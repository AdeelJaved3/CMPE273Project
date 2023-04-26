package com.sjsu.HealthConnect.service;

import com.sjsu.HealthConnect.entity.Prescription;
import org.springframework.http.ResponseEntity;

public interface PrescriptionService {
    ResponseEntity<Object> getPrescriptionsByUser(Long id);
    ResponseEntity<Object> getPrescriptionById(Long id);
    ResponseEntity<Object> createPrescription(Prescription prescription);
}
