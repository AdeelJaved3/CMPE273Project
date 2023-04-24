package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.DoctorProfile;
import com.sjsu.HealthConnect.entity.PatientProfile;
import com.sjsu.HealthConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {

    Optional<PatientProfile> findByUser(User user);
}
