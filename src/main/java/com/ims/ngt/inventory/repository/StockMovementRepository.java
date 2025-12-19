package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.StockMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    Page<StockMovement>
    findByProductIdAndWarehouseIdAndCreatedAtBetweenOrderByCreatedAtAsc(
            Long productId,
            Long warehouseId,
            LocalDateTime from,
            LocalDateTime to,
            Pageable pageable
    );

    // Needed to calculate opening balance
    java.util.List<StockMovement>
    findByProductIdAndWarehouseIdAndCreatedAtBefore(
            Long productId,
            Long warehouseId,
            LocalDateTime before
    );
}
