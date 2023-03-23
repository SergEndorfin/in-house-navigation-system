package com.itkon.navigationsystem.controllers;

import com.itkon.navigationsystem.dto.BaseStationReportDto;
import com.itkon.navigationsystem.dto.MobileStationDistanceReportDto;
import com.itkon.navigationsystem.dto.NoConnectionMsReportDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportsController.class)
class ReportsControllerTest {

    private final String uuid = "uuid";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ReportsController reportsControllerMock;

    @Test
    void whenUnexpectedExceptionThrown_thenHandleIt() throws Exception {
        when(reportsControllerMock.getBaseStationReports(anyString())).thenThrow(new RuntimeException());
        this.mockMvc.perform(post("/reports/123"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Huston, everything is gone!")));
    }

    @Test
    void getBaseStationReports() throws Exception {
        when(reportsControllerMock.getBaseStationReports(anyString()))
                .thenReturn(new BaseStationReportDto(
                        uuid,
                        Collections.singletonList(
                                new MobileStationDistanceReportDto(uuid, 11.1, new Timestamp(1))))
                );
        String expectedResponseBody = "{\"base_station_id\":\"uuid\",\"reports\":[{\"distance\":11.1,\"timestamp\":\"1970-01-01T00:00:00.001+00:00\",\"mobile_station_id\":\"uuid\"}]}";
        this.mockMvc.perform(post("/reports/" + uuid))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponseBody)));
    }

    @Test
    void getMobileStationReport() throws Exception {
        when(reportsControllerMock.getMobileStationReport(anyString()))
                .thenReturn(Collections.singletonList(
                        new NoConnectionMsReportDto(uuid, 1.1, 2.2, 12.0, 0, "description"))
                );
        String expectedResponseBody = "[{\"x\":1.1,\"y\":2.2,\"mobileId\":\"uuid\",\"error_radius\":12.0,\"error_code\":0,\"error_description\":\"description\"}]";
        this.mockMvc.perform(get("/location/" + uuid))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponseBody)));
    }
}