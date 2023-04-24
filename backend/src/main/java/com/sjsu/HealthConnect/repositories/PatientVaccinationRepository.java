package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.PatientVaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientVaccinationRepository extends JpaRepository<PatientVaccination, Long> {
    // add custom methods here if needed
}

