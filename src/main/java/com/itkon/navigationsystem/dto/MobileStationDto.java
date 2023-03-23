package com.itkon.navigationsystem.dto;

public class MobileStationDto {

    private String id;
    private double lastKnownX;
    private double lastKnownY;

    public MobileStationDto(String id, double lastKnownX, double lastKnownY) {
        this.id = id;
        this.lastKnownX = lastKnownX;
        this.lastKnownY = lastKnownY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLastKnownX() {
        return lastKnownX;
    }

    public void setLastKnownX(double lastKnownX) {
        this.lastKnownX = lastKnownX;
    }

    public double getLastKnownY() {
        return lastKnownY;
    }

    public void setLastKnownY(double lastKnownY) {
        this.lastKnownY = lastKnownY;
    }
}
