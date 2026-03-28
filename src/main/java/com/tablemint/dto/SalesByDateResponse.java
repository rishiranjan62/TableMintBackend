package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SalesByDateResponse {

    private LocalDate date;
    private long orderCount;
    private BigDecimal totalAmount;
}
