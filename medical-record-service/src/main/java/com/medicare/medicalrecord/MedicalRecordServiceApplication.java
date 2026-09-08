package com.medicare.medicalrecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class của medical-record-service — Quản lý hồ sơ bệnh án
 * Chạy độc lập trên port 8084
 */
@SpringBootApplication
public class MedicalRecordServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalRecordServiceApplication.class, args);
    }
}
