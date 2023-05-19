package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.EHR;
import com.sjsu.HealthConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EHRRepository extends JpaRepository<EHR, Long> {
    /*
    This function returns list of ehrs for a given patient
     */
    List<EHR> findByPatient(User user);

}