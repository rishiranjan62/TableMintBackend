package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnoutSummaryEntry {
    /**
     * Completed table sessions in range (occupancy ended).
     */
    private long totalVisits;
    private Long uniqueCustomers;
    private int daysInRange;
    private double avgVisitsPerDay;
    private double avgVisitsPerWeek;
}
