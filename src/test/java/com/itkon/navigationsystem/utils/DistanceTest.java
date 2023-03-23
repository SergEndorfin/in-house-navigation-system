package com.itkon.navigationsystem.utils;

import com.itkon.navigationsystem.model.BaseStation;
import com.itkon.navigationsystem.model.MobileStation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceTest {

    @Test
    void getDistance() {
        BaseStation baseStation = new BaseStation();
        baseStation.setX(7);
        baseStation.setY(1);
        MobileStation mobileStation = new MobileStation();
        mobileStation.setLastKnownX(3);
        mobileStation.setLastKnownY(4);
        double distance = new Distance().getDistanceBetween(baseStation, mobileStation);
        assertEquals(5, distance, 0.001);
    }
}