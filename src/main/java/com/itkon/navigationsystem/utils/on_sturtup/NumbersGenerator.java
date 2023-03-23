package com.itkon.navigationsystem.utils.on_sturtup;

import org.springframework.stereotype.Component;

@Component
public class NumbersGenerator {

    public static final int MIN = 0;
    public static final int MAX = 100;

    double getRandomValue() {
        double randomVal = MIN + Math.random() * (MAX - MIN);
        return Math.round(randomVal * 100.0) / 100.0;
    }
}
