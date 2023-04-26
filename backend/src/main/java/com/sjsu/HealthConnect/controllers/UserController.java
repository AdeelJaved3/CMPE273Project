package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.dto.DoctorDTO;
import com.sjsu.HealthConnect.dto.DoctorSearchCriteria;
import com.sjsu.HealthConnect.dto.PatientDTO;
import com.sjsu.HealthConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/doctors")
    public ResponseEntity<Object> createUser(@RequestBody DoctorDTO doctorDTO) {
        return userService.createDoctor(doctorDTO);
    }

    @PostMapping("/patients")
    public ResponseEntity<Object> createUser(@RequestBody PatientDTO patientDTO) {
        return userService.createPatient(patientDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/doctors/search")
    public ResponseEntity<Object> searchDoctors(@RequestBody DoctorSearchCriteria searchCriteria) {
        return userService.searchDoctors(searchCriteria);
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<Object> updateDoctor(@PathVariable(value = "id") Long userId,
                                               @RequestBody DoctorDTO doctorDTO) {
        return userService.updateDoctor(userId, doctorDTO);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Object> updatePatient(@PathVariable(value = "id") Long userId,
                                               @RequestBody PatientDTO patientDTO) {
        return userService.updatePatient(userId, patientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        return userService.deleteUser(userId);
    }
}

