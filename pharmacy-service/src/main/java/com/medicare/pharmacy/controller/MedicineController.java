package com.medicare.pharmacy.controller;

import com.medicare.pharmacy.dto.MedicineDto;
import com.medicare.pharmacy.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tầng Controller — tiếp nhận HTTP request cho pharmacy-service
 * Base URL: /api/medicines
 */
@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    // GET /api/medicines
    @GetMapping
    public ResponseEntity<List<MedicineDto>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    // GET /api/medicines/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MedicineDto> getMedicineById(@PathVariable Long id) {
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }

    // POST /api/medicines
    @PostMapping
    public ResponseEntity<MedicineDto> createMedicine(@RequestBody MedicineDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.createMedicine(dto));
    }

    // PUT /api/medicines/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MedicineDto> updateMedicine(@PathVariable Long id, @RequestBody MedicineDto dto) {
        return ResponseEntity.ok(medicineService.updateMedicine(id, dto));
    }

    // DELETE /api/medicines/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}
