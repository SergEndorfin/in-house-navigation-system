package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.dto.NoConnectionMsReportDto;

import java.util.List;

public interface MobileStationReportService {

    List<NoConnectionMsReportDto> getReport(String stationId);
}