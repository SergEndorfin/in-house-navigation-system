package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.dto.BaseStationReportDto;
import com.itkon.navigationsystem.dto.MobileStationDistanceReportDto;
import com.itkon.navigationsystem.model.BaseStation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseStationReportServiceImpl implements BaseStationReportService {

    private final ReportCreatorService reportCreatorService;

    public BaseStationReportServiceImpl(ReportCreatorService reportCreatorService) {
        this.reportCreatorService = reportCreatorService;
    }

    public BaseStationReportDto getReport(String baseStationId) {
        BaseStation savedBaseStation = reportCreatorService.createAndSaveReports(baseStationId);
        return new BaseStationReportDto(
                savedBaseStation.getUuid(),
                mapReportsEntitiesToDtos(savedBaseStation)
        );
    }


    private static List<MobileStationDistanceReportDto> mapReportsEntitiesToDtos(BaseStation savedBaseStation) {
        return savedBaseStation.getMobileStationDistanceReports().stream()
                .map(report -> MobileStationDistanceReportDto.builder()
                        .setMobileStationId(report.getMobileStationId())
                        .setDistance(report.getDistance())
                        .setTimestamp(report.getTimestamp())
                        .build())
                .collect(Collectors.toList());
    }
}