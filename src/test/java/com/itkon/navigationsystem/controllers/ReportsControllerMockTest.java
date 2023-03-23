package com.itkon.navigationsystem.controllers;

import com.itkon.navigationsystem.dto.BaseStationReportDto;
import com.itkon.navigationsystem.services.BaseStationReportService;
import com.itkon.navigationsystem.services.MobileStationReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportsControllerMockTest {

    @Mock
    BaseStationReportService baseStationReportServiceMock;
    @Mock
    MobileStationReportService mobileStationReportServiceMock;
    @InjectMocks
    ReportsController reportsControllerMock;

    private final String uuid = "uuid";

    @Test
    void getBaseStationReports()  {
        when(baseStationReportServiceMock.getReport(anyString())).thenReturn(new BaseStationReportDto());
        reportsControllerMock.getBaseStationReports(uuid);
        verify(baseStationReportServiceMock).getReport(anyString());
    }

    @Test
    void getMobileStationReport() {
        when(mobileStationReportServiceMock.getReport(uuid)).thenReturn(new ArrayList<>());
        reportsControllerMock.getMobileStationReport(uuid);
        verify(mobileStationReportServiceMock).getReport(uuid);
    }
}