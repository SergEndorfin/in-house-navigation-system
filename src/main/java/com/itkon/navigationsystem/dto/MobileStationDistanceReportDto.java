package com.itkon.navigationsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class MobileStationDistanceReportDto {

    @JsonProperty(value = "mobile_station_id")
    private String mobileStationId;
    private double distance;
    private Timestamp timestamp;

    public MobileStationDistanceReportDto(String mobileStationId, double distance, Timestamp timestamp) {
        this.mobileStationId = mobileStationId;
        this.distance = distance;
        this.timestamp = timestamp;
    }

    private MobileStationDistanceReportDto() {
    }

    public String getMobileStationId() {
        return mobileStationId;
    }

    public double getDistance() {
        return distance;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public static Builder builder() {
        return new MobileStationDistanceReportDto().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setMobileStationId(String mobileStationId) {
            MobileStationDistanceReportDto.this.mobileStationId = mobileStationId;
            return this;
        }

        public Builder setDistance(double distance) {
            MobileStationDistanceReportDto.this.distance = distance;
            return this;
        }

        public Builder setTimestamp(Timestamp timestamp) {
            MobileStationDistanceReportDto.this.timestamp = timestamp;
            return this;
        }

        public MobileStationDistanceReportDto build() {
            return MobileStationDistanceReportDto.this;
        }
    }
}
