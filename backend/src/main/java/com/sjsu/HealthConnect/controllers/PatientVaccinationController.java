package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.entity.PatientVaccination;
import com.sjsu.HealthConnect.service.PatientVaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient-vaccinations")
public class PatientVaccinationController {

    @Autowired
    private PatientVaccinationService vaccinationService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllPatientVaccinations() {
        return vaccinationService.getAllPatientVaccinations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatientVaccinationById(@PathVariable int id) {
        return vaccinationService.getPatientVaccinationById(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getVaccinationsOfUser(@PathVariable Long id) {
        return vaccinationService.getPatientVaccinationByPatient(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addPatientVaccination(@RequestBody PatientVaccination patientVaccination) {
        return vaccinationService.createPatientVaccination(patientVaccination);
    }

}
