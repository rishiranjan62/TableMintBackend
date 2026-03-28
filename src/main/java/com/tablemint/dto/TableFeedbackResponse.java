package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TableFeedbackResponse {

    private Long id;
    private Long tableId;
    private int rating;
    private String comment;
    private Instant createdAt;
}
