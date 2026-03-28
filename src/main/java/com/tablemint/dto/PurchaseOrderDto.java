package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PurchaseOrderDto {

    private Long id;
    private Long supplierId;
    private String supplierName;
    private String status;
    private String orderNumber;
    private LocalDate expectedDeliveryDate;
    private String supplierInvoiceNumber;
    private LocalDate supplierInvoiceDate;
    private String notes;
    private Long createdByUserId;
    private Instant createdAt;
    private Instant updatedAt;
    private List<PurchaseOrderLineDto> Lines;
}
