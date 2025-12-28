package com.ims.ngt.inventory.controller;

import com.ims.ngt.inventory.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping("/current")
    public BigDecimal currentStock(
            @RequestParam Long productId,
            @RequestParam Long warehouseId
    ) {
        return service.getCurrentStock(productId, warehouseId);
    }
}

