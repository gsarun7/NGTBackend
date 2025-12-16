package com.ims.ngt.inventory.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PurchaseInvoiceRequestDto {
    private String invoiceNo;
    private LocalDate invoiceDate;
    private Long supplierId;
    private Long warehouseId;
    private List<PurchaseItemDto> items;
}

