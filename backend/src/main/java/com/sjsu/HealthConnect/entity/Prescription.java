package com.sjsu.HealthConnect.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @Column(name = "medication", nullable = false)
    private String medication;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "date_prescribed", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePrescribed;

    // constructors, getters and setters
}
