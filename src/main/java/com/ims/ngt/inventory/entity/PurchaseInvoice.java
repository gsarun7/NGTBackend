package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchase_invoices")
@Data
public class PurchaseInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNo;
    private LocalDate invoiceDate;

    @ManyToOne
    private Supplier supplier;

    private BigDecimal totalAmount;

    private BigDecimal quantity = BigDecimal.ZERO;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "purchaseInvoice", cascade = CascadeType.ALL)
    private List<PurchaseItem> items;
}

