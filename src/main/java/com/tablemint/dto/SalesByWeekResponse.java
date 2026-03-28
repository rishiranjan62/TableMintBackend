package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SalesByWeekResponse {
    private int year;
    private int weekNumber;
    private LocalDate weekStartDate;
    private LocalDate weekEndDate;
    private long orderCount;
    private BigDecimal totalAmount;
}
