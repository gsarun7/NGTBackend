package com.ims.ngt.inventory.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class SalesInvoiceItemsResponseDto {
    private String invoiceNo;
    private LocalDate invoiceDate;
    private BigDecimal totalAmount;
    private Long warehouseId;
    private Long customerId;
    private List<SalesInvoiceItemResponseDto> items;
}
