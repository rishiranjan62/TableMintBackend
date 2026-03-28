package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InventoryDashboardDayDto {

    private LocalDate date;

    /**
     * Quantity consumed (daily usage) , always >=0
     */
    private BigDecimal dailyUsage;
    /**
     * Spoilage / damage lost , always >=0
     */
    private BigDecimal wastage;
    /**
     * Quantity received , always >=0
     */
    private BigDecimal stockIn;

}
