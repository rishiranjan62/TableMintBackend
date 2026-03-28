package com.tablemint.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarkPayrollPaidRequest {

    @NotNull
    private Long userId;

    @NotNull
    @Min(2000)
    @Max(2100)
    private Integer year;

    @NotNull
    @Min(1)
    @Max(12)
    private Integer month;

    /**
     * When set, stored as the paid amount instead of salary × attendance pro-rata.
     * Omit to use calculated pay (requires profile salary for a non-zero calculation).
     */
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be at least zero")
    @DecimalMax(value = "999999999.99", inclusive = true, message = "Amount too large")
    private BigDecimal amountOverride;
}
