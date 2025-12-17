package com.ims.ngt.inventory.controller;

import com.ims.ngt.inventory.dto.StockAdjustmentRequestDto;
import com.ims.ngt.inventory.service.StockAdjustmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/stock")
@RequiredArgsConstructor
public class AdminStockController {

    private final StockAdjustmentService adjustmentService;

    @PostMapping("/adjust")
    public void adjustStock(@RequestBody StockAdjustmentRequestDto dto) {
        adjustmentService.adjustStock(dto);
    }
}
