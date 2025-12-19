package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.CategoryDto;
import com.ims.ngt.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public List<CategoryDto> getAll() {
        return categoryRepo.findAll().stream()
                .map(c -> {
                    CategoryDto dto = new CategoryDto();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    return dto;
                })
                .toList();
    }
}
