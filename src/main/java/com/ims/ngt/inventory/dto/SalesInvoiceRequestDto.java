package com.ims.ngt.inventory.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class SalesInvoiceRequestDto {

    private String invoiceNo;
    private LocalDate invoiceDate;

    // Customer info (NO customerId needed)
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String customerGstNo;

    private Long warehouseId;

    private List<SalesInvoiceItemDto> items;
}
