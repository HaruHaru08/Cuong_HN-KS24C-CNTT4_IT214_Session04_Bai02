package com.medicare.doctor.dto;

import com.medicare.doctor.model.Doctor;
import lombok.*;

/**
 * DTO truyền dữ liệu bác sĩ giữa Controller và Service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {

    private Long id;
    private String fullName;
    private String specialization;
    private String licenseNumber;
    private String phone;
    private String email;
    private String department;

    public static DoctorDto fromEntity(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .fullName(doctor.getFullName())
                .specialization(doctor.getSpecialization())
                .licenseNumber(doctor.getLicenseNumber())
                .phone(doctor.getPhone())
                .email(doctor.getEmail())
                .department(doctor.getDepartment())
                .build();
    }

    public Doctor toEntity() {
        return Doctor.builder()
                .id(this.id)
                .fullName(this.fullName)
                .specialization(this.specialization)
                .licenseNumber(this.licenseNumber)
                .phone(this.phone)
                .email(this.email)
                .department(this.department)
                .build();
    }
}
