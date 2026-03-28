package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackSummaryEntry {
    private long feedbackCount;
    /** Average 1-5 star rating in range : 0 if no feedback .*/
    private double averageRating;
}
