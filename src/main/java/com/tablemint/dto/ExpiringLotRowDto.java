package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ExpiringLotRowDto {

    private Long itemId;
    private String itemName;
    private LocalDate expiryDate;
    private Integer daysUntilExpiry;
    /**
     * Quantity from this receipt (positive delta).
     */
    private BigDecimal quantityReceived;
    private String lotNumber;
    private String quantityUnit;
}
