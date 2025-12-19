package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.StockInventoryRowDto;
import com.ims.ngt.inventory.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockInventoryService {

    private final StockRepository stockRepo;

    public Page<StockInventoryRowDto> getInventory(
            Long categoryId,
            Long warehouseId,
            Pageable pageable
    ) {
        return stockRepo.findInventory(categoryId, warehouseId, pageable);
    }
}
