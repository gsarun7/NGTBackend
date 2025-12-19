package com.ims.ngt.inventory.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockInventoryRowDto {

    private Long productId;
    private String productName;
    private BigDecimal currentStock;
    private String unit;
    private String hsn;
    private LocalDateTime lastUpdated;

    //  REQUIRED constructor for JPQL projection
    public StockInventoryRowDto(
            Long productId,
            String productName,
            BigDecimal currentStock,
            String unit,
            String hsn,
            LocalDateTime lastUpdated
    ) {
        this.productId = productId;
        this.productName = productName;
        this.currentStock = currentStock;
        this.unit = unit;
        this.hsn = hsn;
        this.lastUpdated = lastUpdated;
    }
}
