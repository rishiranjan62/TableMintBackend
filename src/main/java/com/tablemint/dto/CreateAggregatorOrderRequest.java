package com.tablemint.dto;

import com.tablemint.model.AggregatorPlatform;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateAggregatorOrderRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01" , message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Platform is required")
    private AggregatorPlatform platform;

    /** When platform is Other , name of platform ( e.g. Uber eats ) */
    private  String platformOther;

    @NotBlank(message = "Order ID is required")
    private String orderId;

    @NotBlank(message = "Menu items is required")
    private String menuItems;

    private String notes;

    /** Order date for this entry (defaults to today if not provided)*/
    private LocalDate orderDate;

    @Valid
    private List<AggregatorMenuLineRequest> menuLines;
}
