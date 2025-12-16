package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {}
