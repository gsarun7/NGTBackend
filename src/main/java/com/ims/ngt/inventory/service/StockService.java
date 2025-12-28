package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StockService {

    private final StockRepository repo;

    public StockService(StockRepository repo) {
        this.repo = repo;
    }

    public BigDecimal getCurrentStock(Long productId, Long warehouseId) {
        return repo.findCurrentStock(productId, warehouseId)
                .orElse(BigDecimal.ZERO);
    }
}

