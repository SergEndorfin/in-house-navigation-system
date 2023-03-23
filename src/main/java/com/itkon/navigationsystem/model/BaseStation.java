package com.itkon.navigationsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class BaseStation {

    @Id
    private String uuid;
    private String name;
    private double x;
    private double y;
    private double detectionRadiusInMeters;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "base_st__mobile_st_distance_report",
            joinColumns = @JoinColumn(name = "base_station_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "mobile_station_distance_report_uuid", referencedColumnName = "uuid")
    )
    private List<BsToMsDistanceReport> bsToMsDistanceReports;


    public BaseStation(String uuid, String name, double x, double y, double detectionRadiusInMeters) {
        this.uuid = uuid;
        this.name = name;
        this.x = x;
        this.y = y;
        this.detectionRadiusInMeters = detectionRadiusInMeters;
    }

    public BaseStation() {
    }


    public List<BsToMsDistanceReport> getMobileStationDistanceReports() {
        return bsToMsDistanceReports;
    }

    public void setMobileStationDistanceReports(List<BsToMsDistanceReport> bsToMsDistanceReports) {
        this.bsToMsDistanceReports = bsToMsDistanceReports;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getDetectionRadiusInMeters() {
        return detectionRadiusInMeters;
    }

    public void setDetectionRadiusInMeters(double detectionRadiusInMeters) {
        this.detectionRadiusInMeters = detectionRadiusInMeters;
    }

    @Override
    public String toString() {
        return "BaseStation{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", detectionRadiusInMeters=" + detectionRadiusInMeters +
                '}';
    }
}
