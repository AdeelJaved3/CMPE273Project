package com.sjsu.HealthConnect.dto;

import lombok.Data;

@Data
public class DoctorSearchCriteria {
    String specialization;
    String qualification;
    int experience;
}