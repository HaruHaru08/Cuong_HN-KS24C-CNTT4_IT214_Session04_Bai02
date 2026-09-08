package com.medicare.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class của doctor-service — Quản lý thông tin bác sĩ
 * Chạy độc lập trên port 8082
 */
@SpringBootApplication
public class DoctorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorServiceApplication.class, args);
    }
}
