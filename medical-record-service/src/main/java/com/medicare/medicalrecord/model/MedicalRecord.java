package com.medicare.medicalrecord.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity ánh xạ bảng medical_records trong database medicare_medical_record_db
 */
@Entity
@Table(name = "medical_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID bệnh nhân (tham chiếu sang patient-service)
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    // ID bác sĩ phụ trách (tham chiếu sang doctor-service)
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    // Chẩn đoán
    @Column(nullable = false, columnDefinition = "TEXT")
    private String diagnosis;

    // Phương pháp điều trị
    @Column(columnDefinition = "TEXT")
    private String treatment;

    // Ghi chú của bác sĩ
    @Column(columnDefinition = "TEXT")
    private String notes;
}
