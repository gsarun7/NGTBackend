package com.ims.ngt.inventory.controller;


import com.ims.ngt.inventory.dto.*;
import com.ims.ngt.inventory.dto.StockLedgerRowDto;
import com.ims.ngt.inventory.service.StockLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/stock-ledger")
@RequiredArgsConstructor
public class StockLedgerController {

    private final StockLedgerService ledgerService;

    @GetMapping
    public List<StockLedgerRowDto> getLedger(
            @RequestParam Long productId,
            @RequestParam Long warehouseId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        return ledgerService.getLedger(productId, warehouseId, fromDate, toDate);
    }
}

