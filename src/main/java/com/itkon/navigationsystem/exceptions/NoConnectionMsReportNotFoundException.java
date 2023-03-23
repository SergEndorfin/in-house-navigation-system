package com.itkon.navigationsystem.exceptions;

public class NoConnectionMsReportNotFoundException extends RuntimeException {

    public NoConnectionMsReportNotFoundException(String id) {
        super(String.format("MsReport with ID %s not found. Try to look from Success report.", id));
    }
}
