package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.dto.NoConnectionMsReportDto;
import com.itkon.navigationsystem.exceptions.NoConnectionMsReportNotFoundException;
import com.itkon.navigationsystem.exceptions.StationNotFoundException;
import com.itkon.navigationsystem.model.MobileStation;
import com.itkon.navigationsystem.model.NoConnectionMsReport;
import com.itkon.navigationsystem.repos.MobileStationRepository;
import com.itkon.navigationsystem.repos.NoConnectionMsReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobileStationReportServiceImpl implements MobileStationReportService {

    private final MobileStationRepository mobileStationRepository;
    private final NoConnectionMsReportRepository noConnectionMsReportRepository;

    public MobileStationReportServiceImpl(MobileStationRepository mobileStationRepository,
                                          NoConnectionMsReportRepository noConnectionMsReportRepository) {
        this.mobileStationRepository = mobileStationRepository;
        this.noConnectionMsReportRepository = noConnectionMsReportRepository;
    }

    @Override
    public List<NoConnectionMsReportDto> getReport(String stationId) {
        mobileStationRepository.findById(stationId)
                .orElseThrow(() -> new StationNotFoundException(stationId));

        List<NoConnectionMsReport> allByMobileStationId = noConnectionMsReportRepository.findAllByMobileStation_Id(stationId);

        if (allByMobileStationId.isEmpty()) {
            throw new NoConnectionMsReportNotFoundException(stationId);
        }

        return allByMobileStationId.stream()
                .map(foundMsReport -> {
                    MobileStation ms = foundMsReport.getMobileStation();
                    return new NoConnectionMsReportDto(
                            ms.getId(),
                            ms.getLastKnownX(),
                            ms.getLastKnownY(),
                            foundMsReport.getErrorRadius(),
                            foundMsReport.getErrorCode(),
                            foundMsReport.getErrorDescription()
                    );
                }).collect(Collectors.toList());
    }
}