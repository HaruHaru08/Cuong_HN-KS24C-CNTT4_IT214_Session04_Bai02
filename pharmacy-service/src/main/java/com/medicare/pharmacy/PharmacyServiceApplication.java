package com.medicare.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class của pharmacy-service — Quản lý thuốc và đơn thuốc
 * Chạy độc lập trên port 8085
 */
@SpringBootApplication
public class PharmacyServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PharmacyServiceApplication.class, args);
    }
}
