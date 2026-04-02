package com.tablemint.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AggregatorMenuLineRequest {
    @NotNull(message = "menuItemId is required")
    private Long menuItemId;


    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be at least 1")
    private Integer quantity;

}
