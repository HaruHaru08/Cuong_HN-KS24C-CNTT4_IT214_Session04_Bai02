package com.medicare.medicalrecord.controller;

import com.medicare.medicalrecord.dto.MedicalRecordDto;
import com.medicare.medicalrecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tầng Controller — tiếp nhận HTTP request cho medical-record-service
 * Base URL: /api/medical-records
 */
@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    // GET /api/medical-records
    @GetMapping
    public ResponseEntity<List<MedicalRecordDto>> getAllRecords() {
        return ResponseEntity.ok(medicalRecordService.getAllRecords());
    }

    // GET /api/medical-records/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordDto> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getRecordById(id));
    }

    // POST /api/medical-records
    @PostMapping
    public ResponseEntity<MedicalRecordDto> createRecord(@RequestBody MedicalRecordDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.createRecord(dto));
    }

    // PUT /api/medical-records/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordDto> updateRecord(@PathVariable Long id, @RequestBody MedicalRecordDto dto) {
        return ResponseEntity.ok(medicalRecordService.updateRecord(id, dto));
    }

    // DELETE /api/medical-records/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
