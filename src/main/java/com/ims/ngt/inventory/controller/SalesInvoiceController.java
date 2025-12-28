package com.ims.ngt.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ims.ngt.inventory.dto.*;
import com.ims.ngt.inventory.service.SalesInvoiceQueryService;

@RestController
@RequestMapping("/api/sales_invoices")
@RequiredArgsConstructor
public class SalesInvoiceController {

    private final SalesInvoiceQueryService queryService;

    @GetMapping("/{invoiceNo}/items")
    public SalesInvoiceItemsResponseDto getInvoiceItems(
            @PathVariable String invoiceNo) {
        return queryService.getInvoiceItems(invoiceNo);
    }
}
