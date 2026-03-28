package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class SalesDetailsResponse {
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long tableId;
    private List<SalesDetailEntry> orders;
    private long totalOrderCount;
    private BigDecimal totalAmount;
    private int totalPages;
    private int size;
    private int number;
}
