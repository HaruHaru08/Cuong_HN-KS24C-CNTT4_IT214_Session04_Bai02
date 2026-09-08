package com.medicare.pharmacy.repository;

import com.medicare.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Tầng Repository — giao tiếp với database medicare_pharmacy_db
 */
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByNameContainingIgnoreCase(String name);

    List<Medicine> findByStockQuantityGreaterThan(Integer quantity);
}
