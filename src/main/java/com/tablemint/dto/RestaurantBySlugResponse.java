package com.tablemint.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Public response for resolving a restaurant (tenant) by subdomain/slug.
 * Used when  the app is opened at e.g. restaurant1.yourapp.com
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantBySlugResponse {
    private Long restaurantId;
    private String slug;
    private String name;
}
