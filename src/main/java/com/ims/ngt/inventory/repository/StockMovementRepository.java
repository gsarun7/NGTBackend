package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {}
