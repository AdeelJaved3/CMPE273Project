package com.sjsu.HealthConnect.service.impl;

import com.sjsu.HealthConnect.dto.AppointmentStatus;
import com.sjsu.HealthConnect.entity.Appointment;
import com.sjsu.HealthConnect.entity.DoctorProfile;
import com.sjsu.HealthConnect.entity.User;
import com.sjsu.HealthConnect.repositories.AppointmentRepository;
import com.sjsu.HealthConnect.repositories.DoctorProfileRepository;
import com.sjsu.HealthConnect.repositories.UserRepository;
import com.sjsu.HealthConnect.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    @Override
    public ResponseEntity<Object> createAppointment(Appointment appointment) {
        ResponseEntity<Object> response;
        if(isValidAppointment(appointment)){
            appointment.setStatus(AppointmentStatus.SCHEDULED);
            Appointment app = appointmentRepository.save(appointment);
            response = new ResponseEntity<>(app, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("Invalid appointment", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    private boolean isValidAppointment(Appointment appointment){
        Optional<Appointment> app = appointmentRepository.findByPatientIdAndDateAndTimeAndStatus(
                appointment.getPatient().getId(),
                appointment.getDate(),
                appointment.getTime(),
                AppointmentStatus.SCHEDULED);
        if(app.isPresent()){
            return false;
        }
        app = appointmentRepository.findByDoctorIdAndDateAndTimeAndStatus(
                appointment.getDoctor().getId(),
                appointment.getDate(),
                appointment.getTime(),
                AppointmentStatus.SCHEDULED);
        if(app.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    public ResponseEntity<Object> updateAppointment(int id, Appointment appointment) {
        Optional<Appointment> appointment1 = appointmentRepository.findById(id);
        ResponseEntity<Object> response;
        if(appointment1.isPresent()){
            if(isValidAppointment(appointment)){
                appointment.setStatus(AppointmentStatus.SCHEDULED);
                Appointment app = appointmentRepository.save(appointment);
                response = new ResponseEntity<>(app, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("Invalid appointment", HttpStatus.BAD_REQUEST);
            }
        } else {
            response = new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> deleteAppointment(int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        ResponseEntity<Object> response;
        if(appointment.isPresent()){
            Appointment app = appointment.get();
            app.setStatus(AppointmentStatus.CANCELLED);
            response = new ResponseEntity<>("Appointment cancelled", HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("No such appointment", HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public ResponseEntity<Object> appointmentsOfUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        ResponseEntity<Object> response;
        if(user.isPresent()){
            List<Appointment> appointmentList = appointmentRepository.findAllByPatient(user.get());
            response = new ResponseEntity<>(appointmentList, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> availableAppointments(Long doctorId, Date date) {
        ResponseEntity<Object> response;
        Optional<User> userOptional = userRepository.findById(doctorId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            Optional<DoctorProfile> doctorProfile = doctorProfileRepository.findByUser(user);
            if(doctorProfile.isPresent()){
                List<Appointment> appointments = appointmentRepository.findAllByDoctorAndDateAndStatus(user, date, AppointmentStatus.SCHEDULED);
                List<LocalTime> hoursList = appointments.stream()
                        .map(Appointment::getTime)
                        .collect(Collectors.toList());
                LocalTime start = doctorProfile.get().getStartTime(), end=doctorProfile.get().getEndTime();
                List<LocalTime> availableHrs = new ArrayList<>();
                LocalTime current = start;
                while (current.isBefore(end)) {
                    if (!hoursList.contains(current)) {
                        availableHrs.add(current);
                    }
                    current = current.plusHours(1);
                }
                response = new ResponseEntity<>(availableHrs, HttpStatus.OK);
            }else {
                response = new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } else {
            response = new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
