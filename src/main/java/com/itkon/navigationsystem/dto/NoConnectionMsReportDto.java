package com.itkon.navigationsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoConnectionMsReportDto {

    @JsonProperty(value = "mobileId")
    private String mobileStationId;
    private double x;
    private double y;
    @JsonProperty(value = "error_radius")
    private double errorRadius;
    @JsonProperty(value = "error_code")
    private int errorCode;
    @JsonProperty(value = "error_description")
    private String errorDescription;

    public NoConnectionMsReportDto(String mobileStationId, double x, double y,
                                   double errorRadius, int errorCode, String errorDescription) {
        this.mobileStationId = mobileStationId;
        this.x = x;
        this.y = y;
        this.errorRadius = errorRadius;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getMobileStationId() {
        return mobileStationId;
    }

    public void setMobileStationId(String mobileStationId) {
        this.mobileStationId = mobileStationId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getErrorRadius() {
        return errorRadius;
    }

    public void setErrorRadius(double errorRadius) {
        this.errorRadius = errorRadius;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }


}
