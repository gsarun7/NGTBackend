package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventory_adjustment_items")
@Data
public class InventoryAdjustmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private InventoryAdjustment adjustment;

    @ManyToOne
    private Product product;

    private Double quantityChange;
}

