package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.WarehouseDto;
import com.ims.ngt.inventory.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepo;

    public List<WarehouseDto> getAll() {
        return warehouseRepo.findAll().stream()
                .map(w -> {
                    WarehouseDto dto = new WarehouseDto();
                    dto.setId(w.getId());
                    dto.setName(w.getName());
                    return dto;
                })
                .toList();
    }
}
