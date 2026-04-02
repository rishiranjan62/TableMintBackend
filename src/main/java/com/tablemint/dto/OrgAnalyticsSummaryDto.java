package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrgAnalyticsSummaryDto {

    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal totalPaidRevenue;
    private List<OrgRevenueByRestaurantDto> byRestaurant;

}
