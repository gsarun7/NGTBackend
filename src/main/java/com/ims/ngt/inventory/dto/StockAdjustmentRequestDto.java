package com.ims.ngt.inventory.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockAdjustmentRequestDto {

    private Long productId;
    private Long warehouseId;

    // +ve → increase stock, -ve → reduce stock
    private BigDecimal quantityChange;

    private String reason; // optional (damage, correction, audit)
}
