package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RevenueBySourceEntry {
    private long orderCount;
    private BigDecimal totalRevenue;
}
