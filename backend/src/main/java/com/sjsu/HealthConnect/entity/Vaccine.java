package com.sjsu.HealthConnect.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "next_dosage")
    private Vaccine nextDosage;

    @Column(name = "age")
    private int age;

    // constructors, getters and setters
}
