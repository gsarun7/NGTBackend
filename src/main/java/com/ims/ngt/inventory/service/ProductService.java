package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.ProductRequestDto;
import com.ims.ngt.inventory.dto.ProductResponseDto;
import com.ims.ngt.inventory.entity.Category;
import com.ims.ngt.inventory.entity.Product;
import com.ims.ngt.inventory.entity.Unit;
import com.ims.ngt.inventory.repository.CategoryRepository;
import com.ims.ngt.inventory.repository.ProductRepository;
import com.ims.ngt.inventory.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final UnitRepository unitRepo;

    public ProductResponseDto create(ProductRequestDto dto) {

        if (productRepo.existsByNameIgnoreCase(dto.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Product already exists"
            );
        }

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid categoryId: " + dto.getCategoryId()
                ));

        Unit unit = unitRepo.findById(dto.getBaseUnitId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid unitId: " + dto.getBaseUnitId()
                ));

        Product product = new Product();
        product.setName(dto.getName());
        product.setHsn(dto.getHsn());
        product.setCategory(category);
        product.setBaseUnit(unit);
        product.setActive(true);

        productRepo.save(product);

        return ProductResponseDto.from(product);
    }

    public List<ProductResponseDto> getAll() {
        return productRepo.findAll()
                .stream()
                .map(ProductResponseDto::from)
                .toList();
    }

    public List<ProductResponseDto> getByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId)
                .stream()
                .map(ProductResponseDto::from)
                .toList();
    }
}
