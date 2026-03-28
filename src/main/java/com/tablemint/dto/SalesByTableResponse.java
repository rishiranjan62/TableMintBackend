package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class SalesByTableResponse {
    private LocalDate fromDate;
    private LocalDate toDate;
    private long totalOrderCount;
    private List<SalesByTableEntry> byTable;
    private BigDecimal totalAmount;
}
