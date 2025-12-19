package com.ims.ngt.inventory.controller;

import com.ims.ngt.inventory.dto.StockInventoryRowDto;
import com.ims.ngt.inventory.service.StockInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/stock-inventory")
@RequiredArgsConstructor
public class StockInventoryController {

    private final StockInventoryService service;

    @GetMapping
    public Page<StockInventoryRowDto> getInventory(
            @RequestParam Long categoryId,
            @RequestParam Long warehouseId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return service.getInventory(
                categoryId,
                warehouseId,
                PageRequest.of(page, size)
        );
    }
}

