package com.ims.ngt.inventory.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalesReturnItemDto {

    private Long productId;
    private BigDecimal quantity; // quantity being returned
}
