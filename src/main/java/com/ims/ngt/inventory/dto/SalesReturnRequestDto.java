package com.ims.ngt.inventory.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SalesReturnRequestDto {

    private Long salesInvoiceId;
    private Long warehouseId;

    private List<SalesReturnItemDto> items;
}
