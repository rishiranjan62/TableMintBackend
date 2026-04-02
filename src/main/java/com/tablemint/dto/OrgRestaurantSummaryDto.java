package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrgRestaurantSummaryDto {
    private Long id;
    private String name;
    private String slug;
    private Boolean enabled;
    private String city;

}
