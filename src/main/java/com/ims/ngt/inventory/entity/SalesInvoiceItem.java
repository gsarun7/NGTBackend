package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "sales_invoice_items")
@Data
public class SalesInvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SalesInvoice invoice;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Unit unit;

    private BigDecimal quantity;
    private BigDecimal rate;
    private BigDecimal amount;
}
