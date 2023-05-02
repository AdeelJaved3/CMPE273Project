package com.sjsu.HealthConnect.service.impl;

import com.sjsu.HealthConnect.dto.DoctorDTO;
import com.sjsu.HealthConnect.dto.DoctorSearchCriteria;
import com.sjsu.HealthConnect.dto.PatientDTO;
import com.sjsu.HealthConnect.dto.UserDTO;
import com.sjsu.HealthConnect.entity.DoctorProfile;
import com.sjsu.HealthConnect.entity.PatientProfile;
import com.sjsu.HealthConnect.entity.User;
import com.sjsu.HealthConnect.mapper.UserMapper;
import com.sjsu.HealthConnect.repositories.DoctorProfileRepository;
import com.sjsu.HealthConnect.repositories.PatientProfileRepository;
import com.sjsu.HealthConnect.repositories.UserRepository;
import com.sjsu.HealthConnect.service.UserService;
import com.sjsu.HealthConnect.utility.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    @Autowired
    private PatientProfileRepository patientProfileRepository;
    @Autowired
    private UserMapper userMapper;

    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        ResponseEntity<Object> response;
        if (user.isPresent()) {
            UserDTO userDTO = userMapper.userToUserDTO(user.get());
            if("doctor".equalsIgnoreCase(user.get().getRole())){
                Optional<DoctorProfile> dp = doctorProfileRepository.findByUser(user.get());
                DoctorDTO doctorDTO = userMapper.doctorProfileToDoctorDTO(dp.get());
                doctorDTO.setBasicDetails(userDTO);
                response = new ResponseEntity<>(doctorDTO, HttpStatus.OK);
            } else {
                Optional<PatientProfile> patientProfile = patientProfileRepository.findByUser(user.get());
                PatientDTO patientDTO = userMapper.patientProfileToPatientDTO(patientProfile.get());
                patientDTO.setBasicDetails(userDTO);
                response = new ResponseEntity<>(patientDTO, HttpStatus.OK);
            }
        } else {
            response = new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<Object> createDoctor(DoctorDTO doctorDTO) {
        User user = userMapper.doctorDTOToUser(doctorDTO);
        User u = userRepository.save(user);
        DoctorProfile doctorProfile = userMapper.doctorDTOToDoctorProfile(doctorDTO);
        doctorProfile.setUser(u);
        doctorProfileRepository.save(doctorProfile);
        return new ResponseEntity<>("Doctor created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> createPatient(PatientDTO patientDTO) {
        User user = userMapper.patientDTOToUser(patientDTO);
        User u = userRepository.save(user);
        PatientProfile patientProfile = userMapper.patientDTOToPatientProfile(patientDTO);
        patientProfile.setUser(u);
        patientProfileRepository.save(patientProfile);
        return new ResponseEntity<>("Patient created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateDoctor(Long id, DoctorDTO doctorDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        ResponseEntity<Object> response;
        if (userOptional.isPresent()) {
            User user = userMapper.doctorDTOToUser(doctorDTO);
            user.setId(id);
            User u = userRepository.save(user);
            DoctorProfile dp = userMapper.doctorDTOToDoctorProfile(doctorDTO);
            dp.setId(getDoctorProfId(user));
            dp.setUser(u);
            doctorProfileRepository.save(dp);
            response = new ResponseEntity<>("Doctor updated", HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    private Long getDoctorProfId(User user){
        Optional<DoctorProfile> dp = doctorProfileRepository.findByUser(user);
        return dp.get().getId();
    }

    private Long getPatientProfId(User user){
        Optional<PatientProfile> dp = patientProfileRepository.findByUser(user);
        return dp.get().getId();
    }
    @Override
    public ResponseEntity<Object> updatePatient(Long id, PatientDTO patientDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        ResponseEntity<Object> response;
        if (userOptional.isPresent()) {
            User user = userMapper.patientDTOToUser(patientDTO);
            user.setId(id);
            User u = userRepository.save(user);
            PatientProfile pp = userMapper.patientDTOToPatientProfile(patientDTO);
            pp.setId(getPatientProfId(user));
            pp.setUser(u);
            patientProfileRepository.save(pp);
            response = new ResponseEntity<>("Patient updated", HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public ResponseEntity<Object> searchDoctors(DoctorSearchCriteria searchCriteria) {
        List<DoctorProfile> doctors = doctorProfileRepository.findBySearchCriteria(searchCriteria);
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(DoctorProfile doctorProfile: doctors){
            Optional<DoctorProfile> dp = doctorProfileRepository.findById(doctorProfile.getId());
            DoctorDTO doctorDTO = userMapper.doctorProfileToDoctorDTO(doctorProfile);
            doctorDTO.setBasicDetails(userMapper.userToUserDTO(dp.get().getUser()));
            doctorDTOS.add(doctorDTO);
        }
        return new ResponseEntity<>(doctorDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> login(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByUserName(loginDTO.getUsername());
        ResponseEntity<Object> response;
        if(user.isPresent()){
            User u = user.get();
            if(u.getPassword().equals(loginDTO.getPassword())){
                response = new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("Bad credentials", HttpStatus.UNAUTHORIZED);
            }
        } else {
            response = new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
