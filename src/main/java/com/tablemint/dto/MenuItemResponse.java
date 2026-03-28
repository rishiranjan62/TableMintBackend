package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class MenuItemResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String imageUrl;
    private boolean available;
    /**
     * Staff-only: reason shown when 86 (not sent on public menu API).
     */
    private String unavailableNote;
    private boolean vegetarian;
    private boolean glutenFree;
    private List<String> allergens;
    private Instant createdAt;
    /**
     * Present and true when item is soft-deleted (admin List with includeDeleted).
     */
    private Boolean deleted;

}
