package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.exceptions.StationNotFoundException;
import com.itkon.navigationsystem.model.BaseStation;
import com.itkon.navigationsystem.model.MobileStation;
import com.itkon.navigationsystem.model.NoConnectionMsReport;
import com.itkon.navigationsystem.repos.BaseStationRepository;
import com.itkon.navigationsystem.repos.MobileStationRepository;
import com.itkon.navigationsystem.repos.NoConnectionMsReportRepository;
import com.itkon.navigationsystem.utils.Distance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportCreatorServiceImplTest {

    public static final double DISTANCE_LESS_THEN_DETECTION_ZONE_OF_BS = 15.0;
    public static final double DISTANCE_MORE_THEN_DETECTION_ZONE_OF_BS = 35.0;

    @Mock
    BaseStationRepository baseStationRepositoryMock;
    @Mock
    MobileStationRepository mobileStationRepositoryMock;
    @Mock
    NoConnectionMsReportRepository noConnectionMsReportRepositoryMock;
    @Mock
    Distance distanceMock;
    @InjectMocks
    ReportCreatorServiceImpl reportCreatorServiceMock;

    private final String id = "id";
    private final BaseStation baseStation = new BaseStation(id, "name", 11.1, 22.2, 30);
    private final MobileStation mobileStation = new MobileStation(id, 1.1, 2.2);
    private final List<MobileStation> mobileStations = Collections.singletonList(mobileStation);


    @Test
    void whenStationNotFound_thenThrowException() {
        when(baseStationRepositoryMock.findById(id)).thenReturn(Optional.empty());
        StationNotFoundException stationNotFoundException = assertThrows(StationNotFoundException.class, () ->
                reportCreatorServiceMock.createAndSaveReports(id)
        );
        String expectedErrorMessage = "There is no Station with id: id";
        assertEquals(expectedErrorMessage, stationNotFoundException.getMessage());
    }

    @Test
    void whenStationFoundAndMsInDetectionZone_thenAddAndSaveReports() {
        setCommonConditions(DISTANCE_LESS_THEN_DETECTION_ZONE_OF_BS);

        BaseStation savedReport = reportCreatorServiceMock.createAndSaveReports(id);

        setCommonVerifications();
        assertEquals(1, savedReport.getMobileStationDistanceReports().size());
    }

    private void setCommonVerifications() {
        verify(baseStationRepositoryMock).findById(id);
        verify(mobileStationRepositoryMock).findAll();
        verify(distanceMock).getDistanceBetween(baseStation, mobileStation);
        verify(baseStationRepositoryMock).save(baseStation);
    }

    @Test
    void whenStationFoundAndMsNotInDetectionZone_thenAddAndSaveReports() {
        setCommonConditions(DISTANCE_MORE_THEN_DETECTION_ZONE_OF_BS);
        when(noConnectionMsReportRepositoryMock.save(any(NoConnectionMsReport.class)))
                .thenReturn(new NoConnectionMsReport());

        BaseStation savedReport = reportCreatorServiceMock.createAndSaveReports(id);

        setCommonVerifications();
        verify(noConnectionMsReportRepositoryMock).save(any(NoConnectionMsReport.class));
        assertEquals(0, savedReport.getMobileStationDistanceReports().size());
    }

    private void setCommonConditions(double distanceToDetectionZoneOfBs) {
        when(baseStationRepositoryMock.findById(id)).thenReturn(Optional.of(baseStation));
        when(mobileStationRepositoryMock.findAll()).thenReturn(mobileStations);
        when(distanceMock.getDistanceBetween(baseStation, mobileStation)).thenReturn(distanceToDetectionZoneOfBs);
        when(baseStationRepositoryMock.save(baseStation)).thenReturn(baseStation);
    }
}