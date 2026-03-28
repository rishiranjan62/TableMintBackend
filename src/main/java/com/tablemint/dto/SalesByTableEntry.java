package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SalesByTableEntry {
    private Long tableId;
    private long orderCount;
    private String tableNumber;
    private BigDecimal totalAmount;
}
