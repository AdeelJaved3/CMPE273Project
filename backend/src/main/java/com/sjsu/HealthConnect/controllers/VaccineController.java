package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.entity.Vaccine;
import com.sjsu.HealthConnect.service.VaccineService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/")
    public ResponseEntity<Object> getVaccines() {
        return vaccineService.getAllVaccines();
    }

    @GetMapping("/search/")
    public ResponseEntity<Object> searchVaccines(@RequestBody VaccineSearch vaccineSearch) {
        if (vaccineSearch.getName() != null) {
            return vaccineService.getAllVaccinesByName(vaccineSearch.getName());
        }
        return vaccineService.getAllVaccinesByAge(vaccineSearch.getAge());
    }

    @PostMapping("/")
    public ResponseEntity<Object> createVaccine(@RequestBody Vaccine vaccine) {
        return vaccineService.createVaccine(vaccine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVaccine(@PathVariable(value = "id") int id, @RequestBody Vaccine vaccine) {
        return vaccineService.updateVaccine(id, vaccine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVaccineById(@PathVariable(value = "id") int id) {
        return vaccineService.getVaccine(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVaccine(@PathVariable(value = "id") int id) {
        return vaccineService.deleteVaccine(id);
    }
}

@Data
class VaccineSearch {
    String name;
    int age;
}

