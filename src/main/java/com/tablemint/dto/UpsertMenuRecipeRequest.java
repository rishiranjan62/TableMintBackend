package com.tablemint.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpsertMenuRecipeRequest {
    @NotNull
    private Long menuItemId;
    @Size(max = 2000)
    private String notes;
    private BigDecimal yieldLossPercent;
    @NotEmpty
    @Valid
    private List<MenuRecipeLineRequest> Lines;
}
