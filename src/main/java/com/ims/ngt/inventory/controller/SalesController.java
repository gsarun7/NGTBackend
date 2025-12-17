package com.ims.ngt.inventory.controller;

import com.ims.ngt.inventory.dto.SalesInvoiceRequestDto;
import com.ims.ngt.inventory.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @PostMapping
    public ResponseEntity<String> createSale(
            @RequestBody SalesInvoiceRequestDto dto) {

        salesService.createSale(dto);
        return ResponseEntity.ok("Sales invoice created successfully");
    }
}
