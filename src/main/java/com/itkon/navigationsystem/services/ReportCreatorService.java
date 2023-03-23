package com.itkon.navigationsystem.services;

import com.itkon.navigationsystem.model.BaseStation;

public interface ReportCreatorService {
    BaseStation createAndSaveReports(String stationId);
}
