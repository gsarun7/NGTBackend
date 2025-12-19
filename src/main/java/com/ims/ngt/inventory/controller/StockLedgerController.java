package com.ims.ngt.inventory.controller;

import com.ims.ngt.inventory.dto.StockLedgerRowDto;
import com.ims.ngt.inventory.service.StockLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/stock-ledger")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class StockLedgerController {

    private final StockLedgerService stockLedgerService;

    @GetMapping
    public Page<StockLedgerRowDto> getLedger(
            @RequestParam Long productId,
            @RequestParam Long warehouseId,
            @RequestParam LocalDate fromDate,
            @RequestParam LocalDate toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return stockLedgerService.getLedger(
                productId,
                warehouseId,
                fromDate,
                toDate,
                page,
                size
        );
    }
}
