package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.dto.BaseStationReportDto;
import com.itkon.navigationsystem.model.BaseStation;
import com.itkon.navigationsystem.model.BsToMsDistanceReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseStationReportServiceImplTest {

    @Mock
    ReportCreatorService reportCreatorServiceMock;
    @InjectMocks
    BaseStationReportServiceImpl baseStationReportServiceMock;

    @Test
    void getReport() {
        String id = "id";
        String uuid = "uuid";
        String msId = "msId";
        double distance = 10.0;

        List<BsToMsDistanceReport> bsToMsDistanceReports =
                Collections.singletonList(new BsToMsDistanceReport(uuid, msId, distance, new Timestamp(1)));
        BaseStation savedBaseStation = new BaseStation(id, "name", 1.11, 2.22, 10);
        savedBaseStation.setMobileStationDistanceReports(bsToMsDistanceReports);

        when(reportCreatorServiceMock.createAndSaveReports(anyString())).thenReturn(savedBaseStation);
        BaseStationReportDto baseStationReportDto = baseStationReportServiceMock.getReport(id);

        verify(reportCreatorServiceMock).createAndSaveReports(id);
        assertEquals(id, baseStationReportDto.getBaseStationId());
        assertEquals(1, baseStationReportDto.getMobileStationDistanceReports().size());
        assertEquals(msId, baseStationReportDto.getMobileStationDistanceReports().get(0).getMobileStationId());
        assertEquals(distance, baseStationReportDto.getMobileStationDistanceReports().get(0).getDistance());
    }
}