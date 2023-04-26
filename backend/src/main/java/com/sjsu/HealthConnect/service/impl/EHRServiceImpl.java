package com.sjsu.HealthConnect.service.impl;

import com.sjsu.HealthConnect.entity.EHR;
import com.sjsu.HealthConnect.entity.User;
import com.sjsu.HealthConnect.repositories.EHRRepository;
import com.sjsu.HealthConnect.repositories.UserRepository;
import com.sjsu.HealthConnect.service.EHRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EHRServiceImpl implements EHRService {

    @Autowired
    private EHRRepository ehrRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Object> createEHR(EHR ehr) {
        EHR ehr1 = ehrRepository.save(ehr);
        return new ResponseEntity<>(ehr1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getEHRById(Long id) {
        Optional<EHR> ehr = ehrRepository.findById(id);
        ResponseEntity<Object> response;
        if(ehr.isPresent()){
            response = new ResponseEntity<>(ehr, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("No such record", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> getEHRByUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        ResponseEntity<Object> response;
        if(user.isPresent()){
            List<EHR> ehr = ehrRepository.findByPatient(user.get());
            response = new ResponseEntity<>(ehr, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
