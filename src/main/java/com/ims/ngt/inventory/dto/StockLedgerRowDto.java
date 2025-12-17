package com.ims.ngt.inventory.dto;

import com.ims.ngt.inventory.entity.ReferenceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class StockLedgerRowDto {
    private LocalDateTime date;
    private ReferenceType referenceType;
    private Long referenceId;
    private BigDecimal quantityChange;
    private BigDecimal runningBalance;
}