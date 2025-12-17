package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales_invoices")
@Data
public class SalesInvoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNo;
    private LocalDate invoiceDate;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Customer customer;

    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<SalesInvoiceItem> items;
}

