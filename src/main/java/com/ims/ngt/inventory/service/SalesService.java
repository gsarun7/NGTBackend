package com.ims.ngt.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ims.ngt.inventory.dto.*;
import com.ims.ngt.inventory.entity.*;
import com.ims.ngt.inventory.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalesService {


    private final SalesInvoiceRepository invoiceRepo;
    private final SalesInvoiceItemRepository itemRepo;
    private final StockRepository stockRepo;
    private final StockMovementRepository movementRepo;
    private final ProductRepository productRepo;
    private final UnitRepository unitRepo;
    private final CustomerRepository customerRepo;
    private final WarehouseRepository warehouseRepo;

    @Transactional
    public void createSale(SalesInvoiceRequestDto dto) {

        // 1️ Find or create customer
        Customer customer = customerRepo
                .findByPhone(dto.getCustomerPhone())
                .orElseGet(() -> {
                    Customer c = new Customer();
                    c.setName(dto.getCustomerName());
                    c.setPhone(dto.getCustomerPhone());
                    c.setAddress(dto.getCustomerAddress());
                    c.setGstNo(dto.getCustomerGstNo());
                    return customerRepo.save(c);
                });
        Warehouse warehouse = warehouseRepo.findById(dto.getWarehouseId()).orElseThrow();

        SalesInvoice invoice = new SalesInvoice();
        invoice.setInvoiceNo(dto.getInvoiceNo());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setCustomer(customer);
        invoice.setWarehouse(warehouse);

        invoice = invoiceRepo.save(invoice);

        BigDecimal total = BigDecimal.ZERO;

        for (SalesInvoiceItemDto itemDto : dto.getItems()) {



            Product product = productRepo.findById(itemDto.getProductId()).orElseThrow();
            Unit unit = unitRepo.findById(itemDto.getUnitId()).orElseThrow();



            BigDecimal baseQty =
                    itemDto.getQuantity().multiply(unit.getConversionFactor());

            Stock stock = stockRepo
                    .findByProductIdAndWarehouseId(product.getId(), warehouse.getId())
                    .orElseThrow(() -> new RuntimeException("Stock not found"));

            if (stock.getQuantity().compareTo(baseQty) < 0) {
                throw new RuntimeException("Insufficient stock for " + product.getName());
            }

            BigDecimal amount =
                    itemDto.getRate().multiply(itemDto.getQuantity());

            total = total.add(amount);

            SalesInvoiceItem item = new SalesInvoiceItem();
            item.setInvoice(invoice);
            item.setProduct(product);
            item.setUnit(unit);
            item.setQuantity(itemDto.getQuantity());
            item.setRate(itemDto.getRate());
            item.setAmount(amount);
            itemRepo.save(item);

            // ↓ Reduce stock
            stock.setQuantity(stock.getQuantity().subtract(baseQty));
            stockRepo.save(stock);

            // ↓ Stock movement
            StockMovement sm = new StockMovement();
            sm.setProduct(product);
            sm.setWarehouse(warehouse);
            sm.setQuantityChange(baseQty.negate());
            sm.setReferenceType(ReferenceType.SALE);
            sm.setReferenceId(invoice.getId());
            sm.setCreatedAt(LocalDateTime.now());
            movementRepo.save(sm);
        }

        invoice.setTotalAmount(total);
        invoiceRepo.save(invoice);
    }
}

