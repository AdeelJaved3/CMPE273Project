package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // add custom methods here if needed
}

