package com.tablemint.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuItemRequest {

    @NotBlank(message = "Name is required")
    private String name;
    private String description;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be positive")
    private BigDecimal price;
    private String category;
    private String imageUrl;
    @Builder.Default
    private boolean available = true;
    /**
     * Staff-only note when dish is 86 (e.g. sold out reason).
     */
    private String unavailableNote;
    @Builder.Default
    private boolean vegetarian = false;
    @Builder.Default
    private boolean glutenFree = false;
    /**
     * Comma-separated allergen names, e.g. "nuts, dairy, shellfish".
     */
    private String allergens;

}
