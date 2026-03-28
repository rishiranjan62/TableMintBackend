package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ValueAddedInsightsResponse {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String timeZone;

    private List<PeakHourEntry> peakHours;
    private List<TopItemEntry> topOrderedItems;
    private List<MenuItemPrepStatsEntry> avgPrepByMenuItem;
    private TurnoutSummaryEntry turnout;
    private List<TurnOutByDayEntry> turnoutByDay;
    private FeedbackSummaryEntry tableFeedbackSummary;
    private List<MenuItemLikedScoreEntry> topLikedMenuItemsApprox;
    private List<ValuedCustomerEntry> valuedRepeatCustomers;
    /**
     * Short product note for clients: item #7 and future modules.
     */
    private String roadmapNote;
}
