package com.medicare.patient.repository;

import com.medicare.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Tầng Repository — giao tiếp trực tiếp với database medicare_patient_db
 * JpaRepository cung cấp sẵn các thao tác CRUD cơ bản
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Tìm bệnh nhân theo số bảo hiểm
    Optional<Patient> findByInsuranceId(String insuranceId);

    // Tìm bệnh nhân theo số điện thoại
    Optional<Patient> findByPhone(String phone);
}
