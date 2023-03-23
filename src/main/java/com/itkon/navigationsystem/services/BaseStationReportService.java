package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.dto.BaseStationReportDto;

public interface BaseStationReportService {
    BaseStationReportDto getReport(String baseStationId);
}
