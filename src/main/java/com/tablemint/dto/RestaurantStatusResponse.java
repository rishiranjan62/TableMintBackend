package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantStatusResponse {
    private Boolean open;
    private String message;
    private String openTime;
    private String closeTime;

}
