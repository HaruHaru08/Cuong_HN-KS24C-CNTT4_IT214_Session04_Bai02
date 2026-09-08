package com.medicare.appointment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity ánh xạ bảng appointments trong database medicare_appointment_db
 */
@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID bệnh nhân (tham chiếu sang patient-service)
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    // ID bác sĩ (tham chiếu sang doctor-service)
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime appointmentTime;

    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    public enum AppointmentStatus {
        SCHEDULED, COMPLETED, CANCELLED
    }
}
