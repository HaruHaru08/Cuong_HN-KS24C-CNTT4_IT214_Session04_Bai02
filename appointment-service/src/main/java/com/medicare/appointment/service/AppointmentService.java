package com.medicare.appointment.service;

import com.medicare.appointment.dto.AppointmentDto;
import com.medicare.appointment.model.Appointment;
import com.medicare.appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tầng Service — xử lý logic nghiệp vụ cho lịch hẹn khám
 */
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(AppointmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentById(Long id) {
        Appointment appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn với ID: " + id));
        return AppointmentDto.fromEntity(appt);
    }

    @Transactional
    public AppointmentDto createAppointment(AppointmentDto dto) {
        Appointment saved = appointmentRepository.save(dto.toEntity());
        return AppointmentDto.fromEntity(saved);
    }

    @Transactional
    public AppointmentDto updateAppointment(Long id, AppointmentDto dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn với ID: " + id));
        existing.setPatientId(dto.getPatientId());
        existing.setDoctorId(dto.getDoctorId());
        existing.setAppointmentTime(dto.getAppointmentTime());
        existing.setReason(dto.getReason());
        existing.setStatus(dto.getStatus());
        return AppointmentDto.fromEntity(appointmentRepository.save(existing));
    }

    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy lịch hẹn với ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
