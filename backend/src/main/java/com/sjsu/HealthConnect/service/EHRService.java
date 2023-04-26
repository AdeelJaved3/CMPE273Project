package com.sjsu.HealthConnect.service;

import com.sjsu.HealthConnect.entity.EHR;
import org.springframework.http.ResponseEntity;

public interface EHRService {
    ResponseEntity<Object> createEHR(EHR ehr);
    ResponseEntity<Object> getEHRById(Long id);
    ResponseEntity<Object> getEHRByUser(Long id);
}
