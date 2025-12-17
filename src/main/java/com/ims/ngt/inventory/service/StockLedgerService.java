package com.ims.ngt.inventory.service;

import com.ims.ngt.inventory.dto.*;
import com.ims.ngt.inventory.entity.StockMovement;
import com.ims.ngt.inventory.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StockLedgerService {

    private final StockMovementRepository movementRepo;

    public List<StockLedgerRowDto> getLedger(
            Long productId,
            Long warehouseId,
            LocalDate fromDate,
            LocalDate toDate
    ) {

        LocalDateTime from = fromDate.atStartOfDay();
        LocalDateTime to = toDate.atTime(23, 59, 59);

        List<StockMovement> movements =
                movementRepo.findByProductIdAndWarehouseIdAndCreatedAtBetweenOrderByCreatedAtAsc(
                        productId, warehouseId, from, to
                );

        BigDecimal runningBalance = BigDecimal.ZERO;
        List<StockLedgerRowDto> ledger = new ArrayList<>();

        for (StockMovement sm : movements) {
            runningBalance = runningBalance.add(sm.getQuantityChange());

            ledger.add(new StockLedgerRowDto(
                    sm.getCreatedAt(),
                    sm.getReferenceType(),
                    sm.getReferenceId(),
                    sm.getQuantityChange(),
                    runningBalance
            ));
        }

        return ledger;
    }
}
