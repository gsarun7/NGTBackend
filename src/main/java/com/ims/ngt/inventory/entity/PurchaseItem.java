package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "purchase_items")
@Data
public class PurchaseItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PurchaseInvoice purchaseInvoice;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Unit unit;

    private BigDecimal quantity;
    private BigDecimal purchasePrice;
    private BigDecimal amount;
}

