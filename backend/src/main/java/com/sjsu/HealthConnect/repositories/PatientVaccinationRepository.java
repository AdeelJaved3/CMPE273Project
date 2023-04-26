package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.PatientVaccination;
import com.sjsu.HealthConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientVaccinationRepository extends JpaRepository<PatientVaccination, Integer> {

    List<PatientVaccination> findByPatientId(int patientId);

    List<PatientVaccination> findByVaccineId(int vaccineId);

    PatientVaccination findByPatientIdAndVaccineId(int patientId, int vaccineId);

    List<PatientVaccination> findByPatient(User user);
}
