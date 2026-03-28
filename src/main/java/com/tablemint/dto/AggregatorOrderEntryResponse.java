package com.tablemint.dto;

import com.tablemint.model.AggregatorPlatform;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class AggregatorOrderEntryResponse {

    private  Long id;
    private BigDecimal amount;
    private AggregatorPlatform platform;
    private String platformOther;
    private String orderId;
    private String menuItems;
    private String notes;
    private LocalDate orderDate;
    private Instant createdAt;
}
