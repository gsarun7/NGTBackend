package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.*;
import com.ims.ngt.inventory.entity.*;
import com.ims.ngt.inventory.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalesReturnService {

    private final SalesInvoiceRepository salesInvoiceRepo;
    private final StockRepository stockRepo;
    private final StockMovementRepository movementRepo;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    @Transactional
    public void returnSales(SalesReturnRequestDto dto) {

        SalesInvoice invoice = salesInvoiceRepo.findById(dto.getSalesInvoiceId())
                .orElseThrow(() -> new RuntimeException("Sales invoice not found"));

        Warehouse warehouse = warehouseRepo.findById(dto.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        for (SalesReturnItemDto item : dto.getItems()) {

            Product product = productRepo.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Increase stock
            Stock stock = stockRepo
                    .findByProductIdAndWarehouseId(product.getId(), warehouse.getId())
                    .orElseGet(() -> {
                        Stock s = new Stock();
                        s.setProduct(product);
                        s.setWarehouse(warehouse);
                        s.setQuantity(BigDecimal.ZERO);
                        return s;
                    });

            stock.setQuantity(stock.getQuantity().add(item.getQuantity()));
            stockRepo.save(stock);

            // Stock movement
            StockMovement sm = new StockMovement();
            sm.setProduct(product);
            sm.setWarehouse(warehouse);
            sm.setQuantityChange(item.getQuantity()); // +ve
            sm.setReferenceType(ReferenceType.RETURN);
            sm.setReferenceId(invoice.getId());
            sm.setCreatedAt(LocalDateTime.now());
            movementRepo.save(sm);
        }
    }
}
