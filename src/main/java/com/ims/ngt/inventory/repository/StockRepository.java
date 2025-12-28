package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.dto.StockInventoryRowDto;
import com.ims.ngt.inventory.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductIdAndWarehouseId(
            Long productId, Long warehouseId
    );

    @Query("""
SELECT new com.ims.ngt.inventory.dto.StockInventoryRowDto(
    p.id,
    p.name,
    s.quantity,
    u.name,
    p.hsn,
    MAX(sm.createdAt)
)
FROM Stock s
JOIN s.product p
JOIN p.baseUnit u
LEFT JOIN StockMovement sm 
   ON sm.product = p AND sm.warehouse = s.warehouse
WHERE p.category.id = :categoryId
  AND s.warehouse.id = :warehouseId
GROUP BY p.id, p.name, s.quantity, u.name, p.hsn
""")
    Page<StockInventoryRowDto> findInventory(
            @Param("categoryId") Long categoryId,
            @Param("warehouseId") Long warehouseId,
            Pageable pageable
    );




    @Query("""
    SELECT s.quantity
    FROM Stock s
    WHERE s.product.id = :productId
      AND s.warehouse.id = :warehouseId
  """)
    Optional<BigDecimal> findCurrentStock(
            Long productId,
            Long warehouseId
    );
}
