package com.itkon.navigationsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MobileStation {

    @Id
    private String id;
    private double lastKnownX;
    private double lastKnownY;

    public MobileStation(String id, double lastKnownX, double lastKnownY) {
        this.id = id;
        this.lastKnownX = lastKnownX;
        this.lastKnownY = lastKnownY;
    }

    public MobileStation() {
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
