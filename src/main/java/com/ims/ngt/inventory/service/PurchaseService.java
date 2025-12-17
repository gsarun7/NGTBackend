package com.ims.ngt.inventory.service;


import com.ims.ngt.inventory.dto.*;
import com.ims.ngt.inventory.entity.*;
import com.ims.ngt.inventory.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseInvoiceRepository invoiceRepo;
    private final PurchaseItemRepository itemRepo;
    private final StockRepository stockRepo;
    private final StockMovementRepository movementRepo;
    private final ProductRepository productRepo;
    private final UnitRepository unitRepo;
    private final SupplierRepository supplierRepo;
    private final WarehouseRepository warehouseRepo;

    @Transactional
    public void createPurchase(PurchaseInvoiceRequestDto dto) {

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow();
        Warehouse warehouse = warehouseRepo.findById(dto.getWarehouseId())
                .orElseThrow();

        PurchaseInvoice invoice = new PurchaseInvoice();
        invoice.setInvoiceNo(dto.getInvoiceNo());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setSupplier(supplier);
        invoice.setWarehouse(warehouse);
        BigDecimal total = BigDecimal.ZERO;


        invoice = invoiceRepo.save(invoice);

        for (PurchaseItemDto itemDto : dto.getItems()) {

            Product product = productRepo.findById(itemDto.getProductId())
                    .orElseThrow();
            Unit unit = unitRepo.findById(itemDto.getUnitId())
                    .orElseThrow();

            BigDecimal factor =
                    unit.getConversionFactor() != null
                            ? unit.getConversionFactor()
                            : BigDecimal.ONE;

            BigDecimal baseQty = itemDto.getQuantity().multiply(factor);

            BigDecimal lineAmount =
                    itemDto.getPurchasePrice().multiply(itemDto.getQuantity());
            total = total.add(lineAmount);





            // Save purchase item
            PurchaseItem item = new PurchaseItem();
            item.setAmount(lineAmount);
            item.setPurchaseInvoice(invoice);
            item.setProduct(product);
            item.setUnit(unit);
            item.setQuantity(itemDto.getQuantity());
            item.setPurchasePrice(itemDto.getPurchasePrice());
            itemRepo.save(item);

            // Update stock
            Stock stock = stockRepo
                    .findByProductIdAndWarehouseId(
                            product.getId(), warehouse.getId()
                    )
                    .orElseGet(() -> {
                        Stock s = new Stock();
                        s.setProduct(product);
                        s.setWarehouse(warehouse);
                        s.setQuantity(BigDecimal.ZERO);
                        return s;
                    });

            stock.setQuantity(stock.getQuantity().add(baseQty));
            stockRepo.save(stock);

            // Stock movement
            StockMovement sm = new StockMovement();
            sm.setProduct(product);
            sm.setWarehouse(warehouse);
            sm.setQuantityChange(baseQty);
            sm.setReferenceType(ReferenceType.PURCHASE);
            sm.setReferenceId(invoice.getId());
            sm.setCreatedAt(LocalDateTime.now());
            movementRepo.save(sm);
        }
        invoice.setTotalAmount(total);
        invoiceRepo.save(invoice);
    }
}
