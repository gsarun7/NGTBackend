package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.PurchaseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Long> {}