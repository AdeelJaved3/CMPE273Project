package com.sjsu.HealthConnect.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class DoctorDTO {

    private UserDTO basicDetails;

    private int experience;

    private String specialization;

    private String qualification;

    private String regNo;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime dateCreated;
}
