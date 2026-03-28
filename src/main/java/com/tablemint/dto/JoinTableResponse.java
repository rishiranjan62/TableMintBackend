package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class JoinTableResponse {
    private Long tableId;
    private String tableNumber;
    private String message;
    private Instant joinedAt;
}
