package com.ims.ngt.inventory.dto;


import com.ims.ngt.inventory.entity.Product;
import lombok.Data;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private String hsn;
    private Long categoryId;

    private String categoryName;
    private String unit;

    public static ProductResponseDto from(Product p) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setHsn(p.getHsn());
        dto.setCategoryId(p.getCategory().getId());
        dto.setCategoryName(p.getCategory().getName());
        dto.setUnit(p.getBaseUnit().getName());
        return dto;
    }
}
