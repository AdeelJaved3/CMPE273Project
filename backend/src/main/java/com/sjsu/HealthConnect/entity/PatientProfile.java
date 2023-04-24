package com.sjsu.HealthConnect.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_profile")
@Data
public class PatientProfile {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "family_history")
    private String familyHistory;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "insurance_number", nullable = false, unique = true)
    private String insuranceNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // constructors, getters, setters
}
