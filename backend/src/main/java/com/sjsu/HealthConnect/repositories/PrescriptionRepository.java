package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    // add custom methods here if needed
}

