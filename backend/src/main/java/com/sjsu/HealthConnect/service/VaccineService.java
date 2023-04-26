package com.sjsu.HealthConnect.service;

import com.sjsu.HealthConnect.dto.DoctorDTO;
import com.sjsu.HealthConnect.dto.DoctorSearchCriteria;
import com.sjsu.HealthConnect.dto.PatientDTO;
import com.sjsu.HealthConnect.entity.Vaccine;
import org.springframework.http.ResponseEntity;

public interface VaccineService {
    ResponseEntity<Object> getAllVaccines();
    ResponseEntity<Object> getAllVaccinesByName(String name);
    ResponseEntity<Object> getAllVaccinesByAge(int age);
    ResponseEntity<Object> createVaccine(Vaccine vaccine);
    ResponseEntity<Object> updateVaccine(int id, Vaccine vaccine);
    ResponseEntity<Object> getVaccine(int id);
    ResponseEntity<Object> deleteVaccine(int id);
}
