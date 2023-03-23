package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.dto.ConnectionCodes;
import com.itkon.navigationsystem.exceptions.StationNotFoundException;
import com.itkon.navigationsystem.model.BaseStation;
import com.itkon.navigationsystem.model.BsToMsDistanceReport;
import com.itkon.navigationsystem.model.MobileStation;
import com.itkon.navigationsystem.model.NoConnectionMsReport;
import com.itkon.navigationsystem.repos.BaseStationRepository;
import com.itkon.navigationsystem.repos.MobileStationRepository;
import com.itkon.navigationsystem.repos.NoConnectionMsReportRepository;
import com.itkon.navigationsystem.utils.Distance;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReportCreatorServiceImpl implements ReportCreatorService {

    private final BaseStationRepository baseStationRepository;
    private final MobileStationRepository mobileStationRepository;
    private final NoConnectionMsReportRepository noConnectionMsReportRepository;
    private final Distance distance;

    public ReportCreatorServiceImpl(BaseStationRepository baseStationRepository,
                                    MobileStationRepository mobileStationRepository,
                                    NoConnectionMsReportRepository noConnectionMsReportRepository,
                                    Distance distance) {
        this.baseStationRepository = baseStationRepository;
        this.mobileStationRepository = mobileStationRepository;
        this.noConnectionMsReportRepository = noConnectionMsReportRepository;
        this.distance = distance;
    }

    @Override
    public BaseStation createAndSaveReports(String baseStationId) {
        BaseStation baseStation = baseStationRepository.findById(baseStationId).orElseThrow(
                () -> new StationNotFoundException(baseStationId));

        List<BsToMsDistanceReport> bsToMsDistanceReports = new ArrayList<>();

        mobileStationRepository.findAll().forEach(mobileStation -> {
            double distanceBetweenStations = distance.getDistanceBetween(baseStation, mobileStation);
            boolean isMsInTheBsDetectionZone = baseStation.getDetectionRadiusInMeters() - distanceBetweenStations > 0;
            if (isMsInTheBsDetectionZone) {
                collectSuccessfulConnectionReports(bsToMsDistanceReports, mobileStation, distanceBetweenStations);
            } else {
                saveUnconnectedReport(baseStationId, mobileStation, distanceBetweenStations);
            }
        });
        baseStation.setMobileStationDistanceReports(bsToMsDistanceReports);
        return baseStationRepository.save(baseStation);
    }


    private static void collectSuccessfulConnectionReports(List<BsToMsDistanceReport> bsToMsDistanceReports,
                                                           MobileStation mobileStation, double distanceBetweenStations) {
        bsToMsDistanceReports.add(new BsToMsDistanceReport(
                UUID.randomUUID().toString(),
                mobileStation.getId(),
                distanceBetweenStations,
                new Timestamp(System.currentTimeMillis())
        ));
    }

    private void saveUnconnectedReport(String baseStationId, MobileStation mobileStation,
                                       double distanceBetweenStations) {
        noConnectionMsReportRepository.save(
                new NoConnectionMsReport(
                        UUID.randomUUID().toString(),
                        distanceBetweenStations,
                        ConnectionCodes.NO_CONNECTION.getCode(),
                        String.format("No connection to Base Station with id: %s. Too large distance.", baseStationId),
                        mobileStation
                ));
    }
}