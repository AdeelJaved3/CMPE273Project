package com.sjsu.HealthConnect.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String gender;

    private LocalDate dateOfBirth;

    private String userName;

    private String role;

    private String password;

    private LocalDateTime dateCreated;
}
