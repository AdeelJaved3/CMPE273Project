package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.entity.EHR;
import com.sjsu.HealthConnect.entity.Prescription;
import com.sjsu.HealthConnect.service.EHRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ehr")
public class EHRController {

    @Autowired
    private EHRService ehrService;

    @PostMapping("/")
    public ResponseEntity<Object> createEHR(@RequestBody EHR ehr) {
        return ehrService.createEHR(ehr);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEHRById(@PathVariable(value = "id") Long id) {
        return ehrService.getEHRById(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getEHROfUsers(@PathVariable(value = "id") Long userId) {
        return ehrService.getEHRByUser(userId);
    }
}

