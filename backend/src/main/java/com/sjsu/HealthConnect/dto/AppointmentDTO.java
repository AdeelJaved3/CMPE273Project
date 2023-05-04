package com.sjsu.HealthConnect.dto;

import com.sjsu.HealthConnect.utility.AppointmentType;
import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;


@Data
public class AppointmentDTO {
    private int id;

    private AppointmentType appointmentType;

    private Date date;

    private LocalTime time;
}
