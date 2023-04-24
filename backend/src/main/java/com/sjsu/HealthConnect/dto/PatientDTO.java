package com.sjsu.HealthConnect.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientDTO {

    private UserDTO basicDetails;

    private String address;

    private String allergies;

    private String additionalInfo;

    private String familyHistory;

    private LocalDateTime dateCreated;

    private String insuranceNumber;


}
