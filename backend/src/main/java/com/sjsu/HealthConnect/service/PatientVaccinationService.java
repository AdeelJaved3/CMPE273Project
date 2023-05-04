package com.sjsu.HealthConnect.service;

import java.util.List;

import com.sjsu.HealthConnect.entity.PatientVaccination;
import org.springframework.http.ResponseEntity;

public interface PatientVaccinationService {

    ResponseEntity<Object> getAllPatientVaccinations();

    ResponseEntity<Object> getPatientVaccinationById(int id);

    ResponseEntity<Object> getPatientVaccinationByPatient(Long patient_id);

    ResponseEntity<Object> createPatientVaccination(int appId);
}