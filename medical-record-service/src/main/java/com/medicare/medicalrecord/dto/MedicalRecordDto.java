package com.medicare.medicalrecord.dto;

import com.medicare.medicalrecord.model.MedicalRecord;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO truyền dữ liệu hồ sơ bệnh án giữa Controller và Service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDto {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;

    public static MedicalRecordDto fromEntity(MedicalRecord record) {
        return MedicalRecordDto.builder()
                .id(record.getId())
                .patientId(record.getPatientId())
                .doctorId(record.getDoctorId())
                .recordDate(record.getRecordDate())
                .diagnosis(record.getDiagnosis())
                .treatment(record.getTreatment())
                .notes(record.getNotes())
                .build();
    }

    public MedicalRecord toEntity() {
        return MedicalRecord.builder()
                .id(this.id)
                .patientId(this.patientId)
                .doctorId(this.doctorId)
                .recordDate(this.recordDate != null ? this.recordDate : LocalDate.now())
                .diagnosis(this.diagnosis)
                .treatment(this.treatment)
                .notes(this.notes)
                .build();
    }
}
