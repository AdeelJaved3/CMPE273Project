package com.sjsu.HealthConnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "ehr")
public class EHR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ehr_id")
    private int ehrId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "symptom")
    private String symptom;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment")
    private String treatment;

    @Column(name = "notes")
    private String notes;

    // constructors, getters, setters
}
