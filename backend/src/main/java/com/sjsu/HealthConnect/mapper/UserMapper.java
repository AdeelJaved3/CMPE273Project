package com.sjsu.HealthConnect.mapper;

import com.sjsu.HealthConnect.dto.DoctorDTO;
import com.sjsu.HealthConnect.dto.PatientDTO;
import com.sjsu.HealthConnect.dto.UserDTO;
import com.sjsu.HealthConnect.entity.DoctorProfile;
import com.sjsu.HealthConnect.entity.PatientProfile;
import com.sjsu.HealthConnect.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public DoctorProfile doctorDTOToDoctorProfile(DoctorDTO doctorDTO){
        return modelMapper.map(doctorDTO, DoctorProfile.class);
    }

    public DoctorDTO doctorProfileToDoctorDTO(DoctorProfile doctorProfile){
        return modelMapper.map(doctorProfile, DoctorDTO.class);
    }

    public PatientDTO patientProfileToPatientDTO(PatientProfile patientProfile){
        return modelMapper.map(patientProfile, PatientDTO.class);
    }

    public User doctorDTOToUser(DoctorDTO doctorDTO){
        return modelMapper.map(doctorDTO.getBasicDetails(), User.class);
    }

    public PatientProfile patientDTOToPatientProfile(PatientDTO patientDTO){
        return modelMapper.map(patientDTO, PatientProfile.class);
    }

    public User patientDTOToUser(PatientDTO patientDTO){
        return modelMapper.map(patientDTO.getBasicDetails(), User.class);
    }

    public UserDTO userToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}

