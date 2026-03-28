package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class InventoryAnalyticsDashboardDto {

    private LocalDate from;
    private LocalDate to;
    private String timeZone;
    /** Inclusive window end for expiry list (e.g. today +14). */
    private LocalDate expiringThrough;
    private List<LowStockRowDto> lowStock;
    private List<ExpiringLotRowDto> expiringSoon;
    private List<InventoryVarianceRowDto> topVariance;
    private List<InventoryCategorySpendRowDto> spendByCategory;

}
