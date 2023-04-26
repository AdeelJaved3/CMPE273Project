package com.sjsu.HealthConnect.service.impl;

import java.util.List;
import java.util.Optional;

import com.sjsu.HealthConnect.entity.User;
import com.sjsu.HealthConnect.repositories.PatientVaccinationRepository;
import com.sjsu.HealthConnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sjsu.HealthConnect.entity.PatientVaccination;
import com.sjsu.HealthConnect.service.PatientVaccinationService;

@Service
public class PatientVaccinationServiceImpl implements PatientVaccinationService {

    @Autowired
    private PatientVaccinationRepository patVacRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> getAllPatientVaccinations() {
        List<PatientVaccination> patientVaccinations = patVacRepository.findAll();
        return new ResponseEntity<>(patientVaccinations, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getPatientVaccinationById(int id) {
        Optional<PatientVaccination> patientVaccination = patVacRepository.findById(id);
        ResponseEntity<Object> response;
        if (patientVaccination.isPresent()) {
            response = new ResponseEntity<>(patientVaccination.get(), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Patient vaccination record not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> getPatientVaccinationByPatient(Long patient_id) {
        Optional<User> userOptional = userRepository.findById(patient_id);
        ResponseEntity<Object> response;
        if(userOptional.isPresent()){
            List<PatientVaccination> vaccination = patVacRepository.findByPatient(userOptional.get());
            response = new ResponseEntity<>(vaccination, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("No such user found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> createPatientVaccination(PatientVaccination PatientVaccination) {
        PatientVaccination vaccination = patVacRepository.save(PatientVaccination);
        return new ResponseEntity<>(vaccination, HttpStatus.CREATED);
    }

   // @Override
    public ResponseEntity<Object> updatePatientVaccination(int id, PatientVaccination patientVaccination) {
        Optional<PatientVaccination> vaccination = patVacRepository.findById(id);
        ResponseEntity<Object> response;
        if (vaccination.isPresent()) {
            patientVaccination.setId(id);
            PatientVaccination vacc = patVacRepository.save(patientVaccination);
            response = new ResponseEntity<>(vacc, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Patient vaccination record not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    //@Override
    public ResponseEntity<Object> deletePatientVaccination(int id) {
        Optional<PatientVaccination> patientVaccinationOptional = patVacRepository.findById(id);
        ResponseEntity<Object> response;
        if (patientVaccinationOptional.isPresent()) {
            patVacRepository.deleteById(id);
            response = new ResponseEntity<>("Deleted the vaccination report", HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Vaccination record is not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
