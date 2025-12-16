package com.ims.ngt.inventory.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseItemDto {
    private Long productId;
    private Long unitId;
    private BigDecimal quantity;
    private BigDecimal purchasePrice;
}

