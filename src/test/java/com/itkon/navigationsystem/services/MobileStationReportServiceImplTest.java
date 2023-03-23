package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.dto.NoConnectionMsReportDto;
import com.itkon.navigationsystem.exceptions.NoConnectionMsReportNotFoundException;
import com.itkon.navigationsystem.exceptions.StationNotFoundException;
import com.itkon.navigationsystem.model.MobileStation;
import com.itkon.navigationsystem.model.NoConnectionMsReport;
import com.itkon.navigationsystem.repos.MobileStationRepository;
import com.itkon.navigationsystem.repos.NoConnectionMsReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileStationReportServiceImplTest {

    @Mock
    MobileStationRepository mobileStationRepositoryMock;
    @Mock
    NoConnectionMsReportRepository noConnectionMsReportRepositoryMock;
    @InjectMocks
    MobileStationReportServiceImpl mobileStationReportServiceMock;

    private final String id = "id";

    @Test
    void whenGetReportAndStationNotFound_thenThrowStationNotFoundException() {
        when(mobileStationRepositoryMock.findById(id)).thenReturn(Optional.empty());
        StationNotFoundException stationNotFoundException = assertThrows(StationNotFoundException.class, () ->
                mobileStationReportServiceMock.getReport(id)
        );
        String expectedErrorMessage = "There is no Station with id: id";
        assertEquals(expectedErrorMessage, stationNotFoundException.getMessage());
    }

    @Test
    void whenGetReportStationFoundAndNoReports_thenThrowNoConnectionMsReportNotFoundException() {
        when(mobileStationRepositoryMock.findById(id)).thenReturn(Optional.of(new MobileStation()));
        when(noConnectionMsReportRepositoryMock.findAllByMobileStation_Id(id))
                .thenReturn(new ArrayList<>());

        NoConnectionMsReportNotFoundException noConnectionMsReportNotFoundException =
                assertThrows(
                        NoConnectionMsReportNotFoundException.class,
                        () -> mobileStationReportServiceMock.getReport(id)
                );
        verify(mobileStationRepositoryMock).findById(id);
        String expectedErrorMessage = "MsReport with ID id not found. Try to look from Success report.";
        assertEquals(expectedErrorMessage, noConnectionMsReportNotFoundException.getMessage());
    }

    @Test
    void whenGetReportAndStationFound_thenReturnListOfNoConnectionMsReportDtos() {
        String uuid = "uuid";
        double errorRadius = 11.1;
        int errorCode = 0;
        String errorDesc = "errorDesc";
        MobileStation mobileStation = new MobileStation(id, 11.0, 22.0);
        NoConnectionMsReport noConnectionMsReport = new NoConnectionMsReport(uuid, errorRadius, errorCode, errorDesc, mobileStation);
        List<NoConnectionMsReport> allByMobileStationId = Collections.singletonList(noConnectionMsReport);

        when(mobileStationRepositoryMock.findById(id)).thenReturn(Optional.of(new MobileStation()));
        when(noConnectionMsReportRepositoryMock.findAllByMobileStation_Id(id)).thenReturn(allByMobileStationId);

        List<NoConnectionMsReportDto> reports = mobileStationReportServiceMock.getReport(id);
        verify(mobileStationRepositoryMock).findById(id);
        verify(noConnectionMsReportRepositoryMock).findAllByMobileStation_Id(id);
        assertEquals(1, reports.size());
        assertEquals(errorDesc, reports.get(0).getErrorDescription());
    }
}