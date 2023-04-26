package com.sjsu.HealthConnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "patient_vaccination")
public class PatientVaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @Column(name = "dose_number", nullable = false)
    private int doseNumber;

    @Column(name = "date_administered", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdministered;

    @Column(name = "next_dose_date")
    @Temporal(TemporalType.DATE)
    private Date nextDoseDate;
}
