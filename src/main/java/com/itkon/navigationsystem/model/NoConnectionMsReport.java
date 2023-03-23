package com.itkon.navigationsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class NoConnectionMsReport {

    @Id
    private String uuid;
    private double errorRadius;
    private int errorCode;
    private String errorDescription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mobile_station_id")
    private MobileStation mobileStation;

    public NoConnectionMsReport(String uuid, double errorRadius, int errorCode,
                                String errorDescription, MobileStation mobileStation) {
        this.uuid = uuid;
        this.errorRadius = errorRadius;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.mobileStation = mobileStation;
    }

    public MobileStation getMobileStation() {
        return mobileStation;
    }

    public void setMobileStation(MobileStation mobileStationId) {
        this.mobileStation = mobileStationId;
    }

    public NoConnectionMsReport() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    @Override
    public String toString() {
        return "NoConnectionMsReport{" +
                "uuid='" + uuid + '\'' +
                ", errorRadius=" + errorRadius +
                ", errorCode=" + errorCode +
                ", errorDescription='" + errorDescription + '\'' +
                ", mobileStation=" + mobileStation +
                '}';
    }
}
