package com.itkon.navigationsystem.exceptions;

public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException(String id) {
        super("There is no Station with id: " + id);
    }
}
