package com.itkon.navigationsystem.dto;

public enum ConnectionCodes {
    CONNECTED(1),
    NO_CONNECTION(0);

    private final int code;

    ConnectionCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}