package com.ims.ngt.inventory.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String hsn;
    private String category;
    private String baseUnit;
    private boolean active;
}
