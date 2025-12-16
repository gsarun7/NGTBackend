package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String hsn;
    private boolean active = true;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Unit baseUnit;
}

