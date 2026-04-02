package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrgOrganizationDto {

    private Long id;
    private String name;
    private String slug;
    private int linkedRestaurantCount;
}
