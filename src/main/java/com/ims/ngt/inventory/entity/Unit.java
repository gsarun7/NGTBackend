package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "units")
@Data
public class Unit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;      // Box, Sqft, Kg
    private String symbol;    // box, sqft, kg

    @ManyToOne
    private Unit baseUnit;    // nullable

    private BigDecimal conversionFactor;
}
