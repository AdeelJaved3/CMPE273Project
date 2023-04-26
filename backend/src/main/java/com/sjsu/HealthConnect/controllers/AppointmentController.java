package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.dto.AppSearchDTO;
import com.sjsu.HealthConnect.entity.Appointment;
import com.sjsu.HealthConnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/")
    public ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable(value = "id") int id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> cancelAppointment(@PathVariable(value = "id") int id) {
        return appointmentService.deleteAppointment(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getAppointmentsOfUser(@PathVariable(value = "id") Long id) {
        return appointmentService.appointmentsOfUser(id);
    }

    @GetMapping("/doctors")
    public ResponseEntity<Object> getAppointmentsOfUser(@RequestBody AppSearchDTO searchDTO) {
        return appointmentService.availableAppointments(searchDTO.getDoctorId(), searchDTO.getDate());
    }

}

