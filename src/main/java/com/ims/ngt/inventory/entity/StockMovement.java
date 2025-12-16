package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
@Data
public class StockMovement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;
    // SALE, PURCHASE, ADJUSTMENT, RETURN

    private Long referenceId;
    private BigDecimal quantityChange;

    private LocalDateTime createdAt = LocalDateTime.now();
}
