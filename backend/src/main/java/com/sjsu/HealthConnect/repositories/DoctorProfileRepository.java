package com.sjsu.HealthConnect.repositories;

import com.sjsu.HealthConnect.dto.DoctorSearchCriteria;
import com.sjsu.HealthConnect.entity.DoctorProfile;
import com.sjsu.HealthConnect.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {

    Optional<DoctorProfile> findByUser(User user);

    default List<DoctorProfile> findBySearchCriteria(DoctorSearchCriteria searchCriteria) {
        Specification<DoctorProfile> specification = Specification.where(null);

        if (searchCriteria.getSpecialization() != null && !searchCriteria.getSpecialization().isEmpty()) {
            specification = specification.and(DoctorProfileSpecification.hasSpecialization(searchCriteria.getSpecialization()));
        }
        if (searchCriteria.getQualification() != null && !searchCriteria.getQualification().isEmpty()) {
            specification = specification.and(DoctorProfileSpecification.hasQualification(searchCriteria.getQualification()));
        }
        if (searchCriteria.getExperience() > 0) {
            specification = specification.and(DoctorProfileSpecification.hasExperienceGreaterThanEqual(searchCriteria.getExperience()));
        }
        return findAll(specification);
    }

    List<DoctorProfile> findAll(Specification<DoctorProfile> specification);

}
class DoctorProfileSpecification {

    public static Specification<DoctorProfile> hasExperience(int experience) {
        return (root, query, cb) -> cb.equal(root.get("experience"), experience);
    }

    public static Specification<DoctorProfile> hasExperienceGreaterThanEqual(int experience) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), experience);
    }


    public static Specification<DoctorProfile> hasSpecialization(String specialization) {
        return (root, query, cb) -> cb.equal(root.get("specialization"), specialization);
    }

    public static Specification<DoctorProfile> hasQualification(String qualification) {
        return (root, query, cb) -> cb.equal(root.get("qualification"), qualification);
    }

    public static Specification<DoctorProfile> hasRegNo(String regNo) {
        return (root, query, cb) -> cb.equal(root.get("regNo"), regNo);
    }

    public static Specification<DoctorProfile> hasStartTime(LocalTime startTime) {
        return (root, query, cb) -> cb.equal(root.get("startTime"), startTime);
    }

    public static Specification<DoctorProfile> hasEndTime(LocalTime endTime) {
        return (root, query, cb) -> cb.equal(root.get("endTime"), endTime);
    }

    public static Specification<DoctorProfile> hasUser(User user) {
        return (root, query, cb) -> cb.equal(root.get("user"), user);
    }

}

