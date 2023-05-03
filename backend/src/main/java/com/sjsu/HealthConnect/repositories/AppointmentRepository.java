package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.dto.AppointmentStatus;
import com.sjsu.HealthConnect.entity.Appointment;
import com.sjsu.HealthConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Optional<Appointment> findByPatientIdAndDateAndTimeAndStatus(Long patientId, Date date, LocalTime time, AppointmentStatus status);

    Optional<Appointment> findByDoctorIdAndDateAndTimeAndStatus(Long id, Date date, LocalTime time, AppointmentStatus status);

    List<Appointment> findAllByPatient(User user);

    List<Appointment> findAllByDoctorAndDateAndStatus(User user, Date date, AppointmentStatus status);

    List<Appointment> findByDate(Date nextDate);

    List<Appointment> findAllByDoctor(User u);
}

