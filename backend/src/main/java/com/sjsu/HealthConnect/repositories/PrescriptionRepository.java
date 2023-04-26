package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.Prescription;
import com.sjsu.HealthConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPatient(User user);
    // add custom methods here if needed
}

