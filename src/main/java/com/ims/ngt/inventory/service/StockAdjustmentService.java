package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.StockAdjustmentRequestDto;
import com.ims.ngt.inventory.entity.*;
import com.ims.ngt.inventory.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StockAdjustmentService {

    private final StockRepository stockRepo;
    private final StockMovementRepository movementRepo;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    @Transactional
    public void adjustStock(StockAdjustmentRequestDto dto) {

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Warehouse warehouse = warehouseRepo.findById(dto.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Stock stock = stockRepo
                .findByProductIdAndWarehouseId(product.getId(), warehouse.getId())
                .orElseGet(() -> {
                    Stock s = new Stock();
                    s.setProduct(product);
                    s.setWarehouse(warehouse);
                    s.setQuantity(BigDecimal.ZERO);
                    return s;
                });

        BigDecimal newQty = stock.getQuantity().add(dto.getQuantityChange());

        if (newQty.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Stock cannot be negative");
        }

        stock.setQuantity(newQty);
        stockRepo.save(stock);

        // Record stock movement
        StockMovement sm = new StockMovement();
        sm.setProduct(product);
        sm.setWarehouse(warehouse);
        sm.setQuantityChange(dto.getQuantityChange());
        sm.setReferenceType(ReferenceType.ADJUSTMENT);
        sm.setReferenceId(null); // no invoice
        sm.setCreatedAt(LocalDateTime.now());
        movementRepo.save(sm);
    }
}
