package com.itkon.navigationsystem.utils;

import com.itkon.navigationsystem.model.BaseStation;
import com.itkon.navigationsystem.model.MobileStation;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;

@Component
public class Distance {
    public double getDistanceBetween(BaseStation baseStation, MobileStation mobileStation) {
        return Math.round(
                Point2D.distance(
                        baseStation.getX(), baseStation.getY(),
                        mobileStation.getLastKnownX(), mobileStation.getLastKnownY()
                ) * 100
        ) / 100.0;
    }
}