package com.medicare.patient.service;

import com.medicare.patient.dto.PatientDto;
import com.medicare.patient.model.Patient;
import com.medicare.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tầng Service — xử lý toàn bộ logic nghiệp vụ cho bệnh nhân
 * Không giao tiếp trực tiếp với database, ủy quyền cho PatientRepository
 */
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // Lấy danh sách toàn bộ bệnh nhân
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientDto::fromEntity)
                .collect(Collectors.toList());
    }

    // Tìm bệnh nhân theo ID
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id));
        return PatientDto.fromEntity(patient);
    }

    // Tạo bệnh nhân mới
    @Transactional
    public PatientDto createPatient(PatientDto dto) {
        Patient patient = dto.toEntity();
        Patient saved = patientRepository.save(patient);
        return PatientDto.fromEntity(saved);
    }

    // Cập nhật thông tin bệnh nhân
    @Transactional
    public PatientDto updatePatient(Long id, PatientDto dto) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id));

        existing.setFullName(dto.getFullName());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setGender(dto.getGender());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());
        existing.setInsuranceId(dto.getInsuranceId());

        Patient updated = patientRepository.save(existing);
        return PatientDto.fromEntity(updated);
    }

    // Xóa bệnh nhân theo ID
    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bệnh nhân với ID: " + id);
        }
        patientRepository.deleteById(id);
    }
}
