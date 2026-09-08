package com.medicare.doctor.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity ánh xạ bảng doctors trong database medicare_doctor_db
 */
@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String specialization;

    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false)
    private String phone;

    private String email;

    @Column(name = "department")
    private String department;
}
