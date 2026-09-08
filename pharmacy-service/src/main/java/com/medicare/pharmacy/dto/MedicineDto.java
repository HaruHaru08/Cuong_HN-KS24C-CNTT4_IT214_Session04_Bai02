package com.medicare.pharmacy.dto;

import com.medicare.pharmacy.model.Medicine;
import lombok.*;

/**
 * DTO truyền dữ liệu thuốc giữa Controller và Service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDto {

    private Long id;
    private String name;
    private String activeIngredient;
    private String unit;
    private Integer stockQuantity;
    private Double price;
    private String manufacturer;

    public static MedicineDto fromEntity(Medicine medicine) {
        return MedicineDto.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .activeIngredient(medicine.getActiveIngredient())
                .unit(medicine.getUnit())
                .stockQuantity(medicine.getStockQuantity())
                .price(medicine.getPrice())
                .manufacturer(medicine.getManufacturer())
                .build();
    }

    public Medicine toEntity() {
        return Medicine.builder()
                .id(this.id)
                .name(this.name)
                .activeIngredient(this.activeIngredient)
                .unit(this.unit)
                .stockQuantity(this.stockQuantity)
                .price(this.price)
                .manufacturer(this.manufacturer)
                .build();
    }
}
