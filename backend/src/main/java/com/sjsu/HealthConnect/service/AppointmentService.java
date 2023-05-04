package com.sjsu.HealthConnect.service;

import com.sjsu.HealthConnect.dto.AppointmentDTO;
import com.sjsu.HealthConnect.entity.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface AppointmentService {
    ResponseEntity<Object> createAppointment(Appointment appointment);
    ResponseEntity<Object> updateAppointment(int id, AppointmentDTO appointment);
    ResponseEntity<Object> deleteAppointment(int id);
    ResponseEntity<Object> appointmentsOfUser(Long userId);
    ResponseEntity<Object> availableAppointments(Long doctorId, Date date);
}
