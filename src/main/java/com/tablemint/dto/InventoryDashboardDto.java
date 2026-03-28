package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class InventoryDashboardDto {
    private LocalDate from;
    private LocalDate to;
    /** IANA Zone used for calendar days (e.g. Asia/kolkata) */
    private String timeZone;

    private BigDecimal totalDailyUsage;
    private BigDecimal totalWastage;
    private BigDecimal totalStockIn;

    /** Count of adjustment rows in range */
    private int movementCount;

    private List<InventoryDashboardDayDto> byDay;
    private List<InventoryDashboardItemDto> byItem;


}
