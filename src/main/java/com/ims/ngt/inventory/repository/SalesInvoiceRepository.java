package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
}
