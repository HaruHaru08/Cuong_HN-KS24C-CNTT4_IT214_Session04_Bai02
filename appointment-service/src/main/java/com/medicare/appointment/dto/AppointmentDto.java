package com.medicare.appointment.dto;

import com.medicare.appointment.model.Appointment;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO truyền dữ liệu lịch hẹn giữa Controller và Service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private String reason;
    private Appointment.AppointmentStatus status;

    public static AppointmentDto fromEntity(Appointment appt) {
        return AppointmentDto.builder()
                .id(appt.getId())
                .patientId(appt.getPatientId())
                .doctorId(appt.getDoctorId())
                .appointmentTime(appt.getAppointmentTime())
                .reason(appt.getReason())
                .status(appt.getStatus())
                .build();
    }

    public Appointment toEntity() {
        return Appointment.builder()
                .id(this.id)
                .patientId(this.patientId)
                .doctorId(this.doctorId)
                .appointmentTime(this.appointmentTime)
                .reason(this.reason)
                .status(this.status != null ? this.status : Appointment.AppointmentStatus.SCHEDULED)
                .build();
    }
}
