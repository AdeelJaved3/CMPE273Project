package com.sjsu.HealthConnect.controllers;

import com.sjsu.HealthConnect.dto.DoctorDTO;
import com.sjsu.HealthConnect.service.UserService;
import com.sjsu.HealthConnect.utility.LoginDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

}
