package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.dto.AppSearchDTO;
import com.sjsu.HealthConnect.dto.AppointmentDTO;
import com.sjsu.HealthConnect.entity.Appointment;
import com.sjsu.HealthConnect.service.AppointmentService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 The AppointmentController class is a REST API controller that handles requests related to appointments in a
 healthcare system. It provides endpoints for creating, updating, and cancelling appointments, as well as retrieving
 appointments for users and available appointments for doctors.
 */
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MeterRegistry meterRegistry;

    // This method maps the POST requests to create a new appointment
    // It creates a new appointment and returns a response entity indicating success or failure
    @PostMapping("/")
    public ResponseEntity<Object> createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    // This method maps the PUT requests to update an existing appointment
    // It updates an appointment with the given id and returns a response entity indicating success or failure
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable(value = "id") int id, @RequestBody AppointmentDTO appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    // This method maps the DELETE requests to cancel an existing appointment
    // It cancels an appointment with the given id and returns a response entity indicating success or failure
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> cancelAppointment(@PathVariable(value = "id") int id) {
        return appointmentService.deleteAppointment(id);
    }

    // This method maps the GET requests to fetch appointments of a user
    // It fetches all the appointments of the user with the given id and returns them as a response entity
    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getAppointmentsOfUser(@PathVariable(value = "id") Long id) {
        return appointmentService.appointmentsOfUser(id);
    }

    // This method maps the GET requests to fetch available appointments of a doctor on a given date
    // It fetches all the available appointments of the doctor with the given id on the given date and returns them as a response entity
    @GetMapping("/doctors")
    public ResponseEntity<Object> getAppointmentsOfUser(@RequestBody AppSearchDTO searchDTO) {
        return appointmentService.availableAppointments(searchDTO.getDoctorId(), searchDTO.getDate());
    }

}

