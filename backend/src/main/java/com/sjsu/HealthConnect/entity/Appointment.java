package com.sjsu.HealthConnect.entity;

import com.sjsu.HealthConnect.utility.AppointmentType;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType appointmentType;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date time;

    @Column(name = "reason_for_visit")
    private String reasonForVisit;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @Column(name = "dose_number")
    private Integer doseNumber;

    @Column(name = "next_dose_date")
    @Temporal(TemporalType.DATE)
    private Date nextDoseDate;

    @Column(name = "status", nullable = false)
    private String status;

    // constructors, getters and setters
}
