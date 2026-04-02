package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgRestaurantDashboardDto {

    private Long restaurantId;
    private String name;
    private String slug;
    private String city;
    private Boolean enabled;
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal paidRevenue;
    private long paidOrderCount;
    private BigDecimal averageOrderValue;
    /**
     * Top menu lines by quantity (PAID, same date rules as revenue).
     */
    private List<PlatformOrderItemFrequencyDto> topItems;
    /**
     * 1 = highest paid revenue in the organization for this period.
     */
    private int revenueRankInOrganization;
    private int restaurantsInOrganization;
}
