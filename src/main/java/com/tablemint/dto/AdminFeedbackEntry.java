package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AdminFeedbackEntry {

    private Long id;
    private Long tableId;
    private String tableNumber;
    private String customerPhone;
    private int rating;
    private String comment;
    private Instant createdAt;
}
