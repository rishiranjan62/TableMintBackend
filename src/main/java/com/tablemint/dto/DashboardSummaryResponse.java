package com.tablemint.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DashboardSummaryResponse {

    private LocalDate date;
    private long orderCount;
    private BigDecimal totalRevenue;
    private List<TopItemEntry> topItems;
}
