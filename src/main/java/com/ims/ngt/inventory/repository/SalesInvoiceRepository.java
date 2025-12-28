package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
    Optional<SalesInvoice> findByInvoiceNo(String invoiceNo);
}
