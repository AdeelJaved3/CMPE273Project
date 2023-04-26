package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    List<Vaccine> findAllByName(String name);

    @Query(value = "SELECT * FROM vaccine WHERE age <= ?1", nativeQuery = true)
    List<Vaccine> findByMinimumAgeLessThanEqual(int age);
}
