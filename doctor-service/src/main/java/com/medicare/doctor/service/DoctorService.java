package com.medicare.doctor.service;

import com.medicare.doctor.dto.DoctorDto;
import com.medicare.doctor.model.Doctor;
import com.medicare.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tầng Service — xử lý logic nghiệp vụ cho bác sĩ
 */
@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(DoctorDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ với ID: " + id));
        return DoctorDto.fromEntity(doctor);
    }

    @Transactional
    public DoctorDto createDoctor(DoctorDto dto) {
        Doctor saved = doctorRepository.save(dto.toEntity());
        return DoctorDto.fromEntity(saved);
    }

    @Transactional
    public DoctorDto updateDoctor(Long id, DoctorDto dto) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ với ID: " + id));
        existing.setFullName(dto.getFullName());
        existing.setSpecialization(dto.getSpecialization());
        existing.setLicenseNumber(dto.getLicenseNumber());
        existing.setPhone(dto.getPhone());
        existing.setEmail(dto.getEmail());
        existing.setDepartment(dto.getDepartment());
        return DoctorDto.fromEntity(doctorRepository.save(existing));
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy bác sĩ với ID: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
