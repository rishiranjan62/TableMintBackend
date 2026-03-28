package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Dashboard summary with revenue segregated by channel: in-house (QR + Dine-in) vs Online (aggregator).
 */
@Data
@Builder
public class DashboardSummaryBySourceResponse {

    private LocalDate date;
    /**
     * When summary is for a date range.
     */
    private LocalDate fromDate;
    private LocalDate toDate;
    /**
     * Revenue from QR and Dine-in orders (tables).
     */
    private RevenueBySourceEntry inHouse;
    /**
     * Revenue from manual aggregator entries (Zomato, Swiggy, etc.).
     */
    private RevenueBySourceEntry onlineAggregator;
    private long totalOrderCount;
    private BigDecimal totalRevenue;
    /**
     * Top items from in-house orders only (excludes aggregator placeholder orders).
     */
    private List<TopItemEntry> topItems;
}