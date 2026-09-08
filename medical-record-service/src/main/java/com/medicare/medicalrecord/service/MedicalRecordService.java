package com.medicare.medicalrecord.service;

import com.medicare.medicalrecord.dto.MedicalRecordDto;
import com.medicare.medicalrecord.model.MedicalRecord;
import com.medicare.medicalrecord.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tầng Service — xử lý logic nghiệp vụ cho hồ sơ bệnh án
 */
@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecordDto> getAllRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(MedicalRecordDto::fromEntity)
                .collect(Collectors.toList());
    }

    public MedicalRecordDto getRecordById(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ bệnh án với ID: " + id));
        return MedicalRecordDto.fromEntity(record);
    }

    @Transactional
    public MedicalRecordDto createRecord(MedicalRecordDto dto) {
        MedicalRecord saved = medicalRecordRepository.save(dto.toEntity());
        return MedicalRecordDto.fromEntity(saved);
    }

    @Transactional
    public MedicalRecordDto updateRecord(Long id, MedicalRecordDto dto) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ bệnh án với ID: " + id));
        existing.setPatientId(dto.getPatientId());
        existing.setDoctorId(dto.getDoctorId());
        existing.setRecordDate(dto.getRecordDate());
        existing.setDiagnosis(dto.getDiagnosis());
        existing.setTreatment(dto.getTreatment());
        existing.setNotes(dto.getNotes());
        return MedicalRecordDto.fromEntity(medicalRecordRepository.save(existing));
    }

    @Transactional
    public void deleteRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy hồ sơ bệnh án với ID: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }
}
