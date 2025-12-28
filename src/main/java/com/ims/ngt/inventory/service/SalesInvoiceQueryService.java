package com.ims.ngt.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ims.ngt.inventory.dto.*;
import com.ims.ngt.inventory.entity.*;
import com.ims.ngt.inventory.repository.*;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesInvoiceQueryService {

    private final SalesInvoiceRepository invoiceRepo;
    private final SalesInvoiceItemRepository itemRepo;

    public SalesInvoiceItemsResponseDto getInvoiceItems(String invoiceNo) {

        SalesInvoice invoice = invoiceRepo.findByInvoiceNo(invoiceNo)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        SalesInvoiceItemsResponseDto resp = new SalesInvoiceItemsResponseDto();
        resp.setInvoiceNo(invoice.getInvoiceNo());
        resp.setInvoiceDate(invoice.getInvoiceDate());
        resp.setTotalAmount(invoice.getTotalAmount());
        resp.setWarehouseId(invoice.getWarehouse().getId());
        resp.setCustomerId(invoice.getCustomer().getId());

        resp.setItems(
                itemRepo.findByInvoiceId(invoice.getId())
                        .stream()
                        .map(item -> {
                            SalesInvoiceItemResponseDto d = new SalesInvoiceItemResponseDto();
                            d.setProductId(item.getProduct().getId());
                            d.setProductName(item.getProduct().getName());
                            d.setUnitId(item.getUnit().getId());
                            d.setUnitName(item.getUnit().getName());
                            d.setQuantity(item.getQuantity());
                            d.setRate(item.getRate());
                            d.setAmount(item.getAmount());
                            return d;
                        })
                        .collect(Collectors.toList())
        );

        return resp;
    }
}
