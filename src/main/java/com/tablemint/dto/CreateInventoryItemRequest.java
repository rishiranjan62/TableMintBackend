package com.tablemint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateInventoryItemRequest {

    @Size(max = 64)
    private String skuCode;

    @NotBlank
    @Size(max = 512)
    private String name;

    @Size(max = 2000)
    private String description;

    private BigDecimal reorderPoint;
    private BigDecimal parLevel;

    /** Raw meat , green vegetables etc. - shown in Perishables section*/
    private Boolean shortShelfLife;

    /** Optional: kg,g,L,pcs etc. */
    @Size(max = 32)
    private String quantityUnit;

    /** Optional Spend / reporting bucket (e.g. produce , Dairy).  */
    @Size(max = 80)
    private String inventoryCategory;

}
