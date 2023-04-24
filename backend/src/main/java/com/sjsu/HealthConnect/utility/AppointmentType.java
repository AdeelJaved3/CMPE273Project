package com.sjsu.HealthConnect.utility;

public enum AppointmentType {
    DOCTOR("doctor"),
    VACCINATION("vaccination");

    private final String type;

    AppointmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
