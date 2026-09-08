package com.medicare.doctor.repository;

import com.medicare.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Tầng Repository — giao tiếp với database medicare_doctor_db
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByLicenseNumber(String licenseNumber);

    java.util.List<Doctor> findBySpecialization(String specialization);
}
