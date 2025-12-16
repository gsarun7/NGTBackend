package com.ims.ngt.inventory.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private String hsn;
    private Long categoryId;
    private Long baseUnitId;
}
