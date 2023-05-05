package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.entity.PatientVaccination;
import com.sjsu.HealthConnect.service.PatientVaccinationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;

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
    public ResponseEntity<Object> addPatientVaccination(@RequestBody VaccinationDTO vaccinationDTO) {
        System.out.println(LocalTime.now());
        ResponseEntity<Object> resp = vaccinationService.createPatientVaccination(vaccinationDTO.getAppId());
        System.out.println(LocalTime.now());
        return resp;

    }

}

@Data
class VaccinationDTO{
    private int appId;
}
