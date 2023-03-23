package com.itkon.navigationsystem.controllers;

import com.itkon.navigationsystem.dto.BaseStationReportDto;
import com.itkon.navigationsystem.dto.NoConnectionMsReportDto;
import com.itkon.navigationsystem.services.BaseStationReportService;
import com.itkon.navigationsystem.services.MobileStationReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportsController {

    private final BaseStationReportService baseStationReportService;
    private final MobileStationReportService mobileStationReportService;

    public ReportsController(BaseStationReportService baseStationReportService,
                             MobileStationReportService mobileStationReportService) {
        this.baseStationReportService = baseStationReportService;
        this.mobileStationReportService = mobileStationReportService;
    }

    @PostMapping("/reports/{uuid}")
    public BaseStationReportDto getBaseStationReports(@PathVariable String uuid) {
        return baseStationReportService.getReport(uuid);
    }

    @GetMapping("/location/{uuid}")
    public List<NoConnectionMsReportDto> getMobileStationReport(@PathVariable String uuid) {
        return mobileStationReportService.getReport(uuid);
    }
}
