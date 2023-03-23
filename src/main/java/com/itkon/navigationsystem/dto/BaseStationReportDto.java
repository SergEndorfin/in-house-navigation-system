package com.itkon.navigationsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BaseStationReportDto {

    @JsonProperty(value = "base_station_id")
    private String baseStationId;
    @JsonProperty(value = "reports")
    private List<MobileStationDistanceReportDto> mobileStationDistanceReports;

    public BaseStationReportDto(String baseStationId, List<MobileStationDistanceReportDto> mobileStationDistanceReports) {
        this.baseStationId = baseStationId;
        this.mobileStationDistanceReports = mobileStationDistanceReports;
    }

    public BaseStationReportDto() {
    }

    public String getBaseStationId() {
        return baseStationId;
    }

    public void setBaseStationId(String baseStationId) {
        this.baseStationId = baseStationId;
    }

    public List<MobileStationDistanceReportDto> getMobileStationDistanceReports() {
        return mobileStationDistanceReports;
    }

    public void setMobileStationDistanceReports(List<MobileStationDistanceReportDto> mobileStationDistanceReportDtos) {
        this.mobileStationDistanceReports = mobileStationDistanceReportDtos;
    }
}
