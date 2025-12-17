package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByProductIdAndWarehouseIdAndCreatedAtBetweenOrderByCreatedAtAsc(
            Long productId,
            Long warehouseId,
            LocalDateTime from,
            LocalDateTime to
    );
}
