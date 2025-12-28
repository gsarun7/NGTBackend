package com.ims.ngt.inventory.controller;

import com.ims.ngt.inventory.dto.SalesReturnRequestDto;
import com.ims.ngt.inventory.service.SalesReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales-return")
@RequiredArgsConstructor
public class SalesReturnController {

    private final SalesReturnService returnService;

    @PostMapping
    public void returnSales(@RequestBody SalesReturnRequestDto dto) {
        returnService.returnSales(dto);
    }
}
