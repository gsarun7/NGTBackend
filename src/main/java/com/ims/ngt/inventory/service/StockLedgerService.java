package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.StockLedgerRowDto;
import com.ims.ngt.inventory.entity.StockMovement;
import com.ims.ngt.inventory.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockLedgerService {

    private final StockMovementRepository movementRepo;

    public Page<StockLedgerRowDto> getLedger(
            Long productId,
            Long warehouseId,
            LocalDate fromDate,
            LocalDate toDate,
            int page,
            int size
    ) {

        LocalDateTime from = fromDate.atStartOfDay();
        LocalDateTime to = toDate.atTime(23, 59, 59);

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());

        // 1️⃣ Get opening balance (before fromDate)
        BigDecimal openingBalance = movementRepo
                .findByProductIdAndWarehouseIdAndCreatedAtBefore(
                        productId, warehouseId, from
                )
                .stream()
                .map(StockMovement::getQuantityChange)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2️⃣ Get paginated movements
        Page<StockMovement> pageResult =
                movementRepo.findByProductIdAndWarehouseIdAndCreatedAtBetweenOrderByCreatedAtAsc(
                        productId, warehouseId, from, to, pageable
                );

        // 3️⃣ Convert to DTO with running balance
        BigDecimal runningBalance = openingBalance;

        List<StockLedgerRowDto> rows = new java.util.ArrayList<>();

        for (StockMovement sm : pageResult.getContent()) {
            runningBalance = runningBalance.add(sm.getQuantityChange());

            rows.add(new StockLedgerRowDto(
                    sm.getCreatedAt(),
                    sm.getReferenceType(),
                    sm.getReferenceId(),
                    sm.getQuantityChange(),
                    runningBalance
            ));
        }

        return new PageImpl<>(
                rows,
                pageable,
                pageResult.getTotalElements()
        );
    }
}
