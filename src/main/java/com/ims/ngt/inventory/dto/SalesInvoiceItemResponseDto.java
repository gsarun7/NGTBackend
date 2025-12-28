package com.ims.ngt.inventory.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalesInvoiceItemResponseDto {
    private Long productId;
    private String productName;
    private Long unitId;
    private String unitName;
    private BigDecimal quantity;
    private BigDecimal rate;
    private BigDecimal amount;
}
