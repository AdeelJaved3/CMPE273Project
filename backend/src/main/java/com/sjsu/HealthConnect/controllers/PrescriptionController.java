package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.entity.Prescription;
import com.sjsu.HealthConnect.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getPrescriptionsByUser(@PathVariable(value = "id") Long userId) {
        return prescriptionService.getPrescriptionsByUser(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPrescriptionById(@PathVariable(value = "id") Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.createPrescription(prescription);
    }
}

