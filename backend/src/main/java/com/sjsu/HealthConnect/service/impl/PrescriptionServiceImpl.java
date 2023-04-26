package com.sjsu.HealthConnect.service.impl;

import com.sjsu.HealthConnect.dto.DoctorDTO;
import com.sjsu.HealthConnect.entity.Prescription;
import com.sjsu.HealthConnect.entity.User;
import com.sjsu.HealthConnect.repositories.PrescriptionRepository;
import com.sjsu.HealthConnect.repositories.UserRepository;
import com.sjsu.HealthConnect.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> getPrescriptionsByUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        ResponseEntity<Object> response;
        if(user.isPresent()){
            List<Prescription> prescriptions = prescriptionRepository.findByPatient(user.get());
            response = new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> getPrescriptionById(Long id) {
        Optional<Prescription> prescription = prescriptionRepository.findById(id);
        ResponseEntity<Object> response;
        if(prescription.isPresent()){
            response = new ResponseEntity<>(prescription.get(), HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("No such prescription", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> createPrescription(Prescription prescription) {
        Prescription pres = prescriptionRepository.save(prescription);
        return new ResponseEntity<>(pres, HttpStatus.CREATED);
    }
}
