package com.medicare.patient.dto;

import com.medicare.patient.model.Patient;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO truyền dữ liệu bệnh nhân giữa các tầng Controller và Service,
 * tránh expose trực tiếp entity ra ngoài API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Patient.Gender gender;
    private String phone;
    private String address;
    private String insuranceId;

    // Chuyển từ Entity sang DTO
    public static PatientDto fromEntity(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .phone(patient.getPhone())
                .address(patient.getAddress())
                .insuranceId(patient.getInsuranceId())
                .build();
    }

    // Chuyển từ DTO sang Entity
    public Patient toEntity() {
        return Patient.builder()
                .id(this.id)
                .fullName(this.fullName)
                .dateOfBirth(this.dateOfBirth)
                .gender(this.gender)
                .phone(this.phone)
                .address(this.address)
                .insuranceId(this.insuranceId)
                .build();
    }
}
