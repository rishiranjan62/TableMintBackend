package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformOrderItemFrequencyDto {

    private Long menuItemId;
    private String menuItemName;
    private Long restaurantId;
    private String restaurantName;
    private long quantitySold;
}
