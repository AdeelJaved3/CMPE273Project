package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    // add custom methods here if needed
}
