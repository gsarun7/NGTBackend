package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.SalesInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesInvoiceItemRepository
        extends JpaRepository<SalesInvoiceItem, Long> {
    List<SalesInvoiceItem> findByInvoiceId(Long invoiceId);
}
