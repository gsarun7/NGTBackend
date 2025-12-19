package com.ims.ngt.inventory.controller;

import com.ims.ngt.inventory.dto.ProductRequestDto;
import com.ims.ngt.inventory.dto.ProductResponseDto;
import com.ims.ngt.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(
            @RequestBody ProductRequestDto dto) {
        return ResponseEntity.ok(productService.create(dto));
    }

    // ✅ SUPPORTS /api/products AND /api/products?categoryId=1
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll(
            @RequestParam(required = false) Long categoryId
    ) {
        if (categoryId != null) {
            return ResponseEntity.ok(productService.getByCategory(categoryId));
        }
        return ResponseEntity.ok(productService.getAll());
    }

}
