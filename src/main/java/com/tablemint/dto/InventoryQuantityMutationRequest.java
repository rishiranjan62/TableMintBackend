package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Positive quantity for usage (consumed), stock-in (received) or damaged (lost).
 */

@Data
public class InventoryQuantityMutationRequest {
    @NotNull
    private Long itemId;

    @NotNull
    private BigDecimal quantity;

    private String reason;


}
