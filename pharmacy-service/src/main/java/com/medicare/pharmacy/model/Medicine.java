package com.medicare.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity ánh xạ bảng medicines trong database medicare_pharmacy_db
 */
@Entity
@Table(name = "medicines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Hoạt chất
    @Column(name = "active_ingredient")
    private String activeIngredient;

    // Đơn vị tính (viên, ml, mg,...)
    @Column(nullable = false)
    private String unit;

    // Số lượng tồn kho
    @Column(nullable = false)
    private Integer stockQuantity;

    // Giá bán
    @Column(nullable = false)
    private Double price;

    private String manufacturer;
}
