package com.itkon.navigationsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "mobile_station_distance_report")
public class BsToMsDistanceReport {

    @Id
    private String uuid;
    private String mobileStationId;
    private double distance;
    private Timestamp timestamp;

    @ManyToMany(mappedBy = "bsToMsDistanceReports")
    List<BaseStation> baseStations;

    public BsToMsDistanceReport(String uuid, String mobileStationId, double distance, Timestamp timestamp) {
        this.uuid = uuid;
        this.mobileStationId = mobileStationId;
        this.distance = distance;
        this.timestamp = timestamp;
    }

    public BsToMsDistanceReport() {
    }

    public String getMobileStationId() {
        return mobileStationId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<BaseStation> getBaseStations() {
        return baseStations;
    }

    public void setBaseStations(List<BaseStation> baseStations) {
        this.baseStations = baseStations;
    }

    public void setMobileStationId(String mobileStationId) {
        this.mobileStationId = mobileStationId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
