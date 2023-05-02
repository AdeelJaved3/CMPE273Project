package com.sjsu.HealthConnect.service;

import com.sjsu.HealthConnect.dto.DoctorDTO;
import com.sjsu.HealthConnect.dto.DoctorSearchCriteria;
import com.sjsu.HealthConnect.dto.PatientDTO;
import com.sjsu.HealthConnect.entity.User;
import com.sjsu.HealthConnect.utility.LoginDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<Object> getAllUsers();
    ResponseEntity<Object> getUserById(Long id);
    ResponseEntity<Object> createDoctor(DoctorDTO doctorDTO);
    ResponseEntity<Object> createPatient(PatientDTO patientDTO);
    ResponseEntity<Object> updateDoctor(Long id, DoctorDTO doctorDTO);
    ResponseEntity<Object> updatePatient(Long id, PatientDTO patientDTO);
    ResponseEntity<Object> deleteUser(Long id);

    ResponseEntity<Object> searchDoctors(DoctorSearchCriteria searchCriteria);
    ResponseEntity<Object> login(LoginDTO loginDTO);
}
