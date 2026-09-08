package com.medicare.pharmacy.service;

import com.medicare.pharmacy.dto.MedicineDto;
import com.medicare.pharmacy.model.Medicine;
import com.medicare.pharmacy.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Tầng Service — xử lý logic nghiệp vụ cho quản lý thuốc
 */
@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public List<MedicineDto> getAllMedicines() {
        return medicineRepository.findAll().stream()
                .map(MedicineDto::fromEntity)
                .collect(Collectors.toList());
    }

    public MedicineDto getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thuốc với ID: " + id));
        return MedicineDto.fromEntity(medicine);
    }

    @Transactional
    public MedicineDto createMedicine(MedicineDto dto) {
        Medicine saved = medicineRepository.save(dto.toEntity());
        return MedicineDto.fromEntity(saved);
    }

    @Transactional
    public MedicineDto updateMedicine(Long id, MedicineDto dto) {
        Medicine existing = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thuốc với ID: " + id));
        existing.setName(dto.getName());
        existing.setActiveIngredient(dto.getActiveIngredient());
        existing.setUnit(dto.getUnit());
        existing.setStockQuantity(dto.getStockQuantity());
        existing.setPrice(dto.getPrice());
        existing.setManufacturer(dto.getManufacturer());
        return MedicineDto.fromEntity(medicineRepository.save(existing));
    }

    @Transactional
    public void deleteMedicine(Long id) {
        if (!medicineRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy thuốc với ID: " + id);
        }
        medicineRepository.deleteById(id);
    }
}
