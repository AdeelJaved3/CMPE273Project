package com.sjsu.HealthConnect.service.impl;

import com.sjsu.HealthConnect.entity.Vaccine;
import com.sjsu.HealthConnect.repositories.VaccineRepository;
import com.sjsu.HealthConnect.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;


    @Override
    public ResponseEntity<Object> getAllVaccines() {
        List<Vaccine> vaccines = vaccineRepository.findAll();
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllVaccinesByName(String name) {
        List<Vaccine> vaccines = vaccineRepository.findAllByName(name);
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllVaccinesByAge(int age) {
        List<Vaccine> vaccines = vaccineRepository.findByMinimumAgeLessThanEqual(age);
        return new ResponseEntity<>(vaccines, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createVaccine(Vaccine vaccine) {
        Vaccine vacc = vaccineRepository.save(vaccine);
        return new ResponseEntity<>(vacc, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateVaccine(int id, Vaccine vaccine) {
        Optional<Vaccine> vaccineOptional = vaccineRepository.findById(id);
        ResponseEntity<Object> response;
        if(vaccineOptional.isPresent()){
            vaccine.setId(vaccineOptional.get().getId());
            Vaccine vacc = vaccineRepository.save(vaccine);
            response = new ResponseEntity<>(vacc, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Vaccine with given id not found", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> getVaccine(int id) {
        Optional<Vaccine> vaccineOptional = vaccineRepository.findById(id);
        ResponseEntity<Object> response;
        if(vaccineOptional.isPresent()){
            response = new ResponseEntity<>(vaccineOptional.get(), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Vaccine with given id not found", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> deleteVaccine(int id) {
        Optional<Vaccine> vaccineOptional = vaccineRepository.findById(id);
        ResponseEntity<Object> response;
        if(vaccineOptional.isPresent()){
            vaccineRepository.deleteById(id);
            response = new ResponseEntity<>("Vaccine removed", HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("Vaccine with given id not found", HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
