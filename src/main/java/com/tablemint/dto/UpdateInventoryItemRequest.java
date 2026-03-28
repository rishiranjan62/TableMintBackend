package com.tablemint.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateInventoryItemRequest {
    @Size(max = 64)
    private String skuCode;
    @Size(max = 512)
    private String name;
    @Size(max = 2000)
    private String description;
    private BigDecimal reorderPoint;
    private BigDecimal parLevel;
    private Boolean shortShelfLife;
    @Size(max = 32)
    private String quantityUnit;
    @Size(max = 80)
    private String inventoryCategory;
    private Boolean active;
}
