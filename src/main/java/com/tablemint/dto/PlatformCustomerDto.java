package com.tablemint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformCustomerDto {
    private Long userId;
    private String phoneNumber;
    private String name;
    /** Visits per restaurant (which restaurants they visited and how many times) . */
    private List<PlatformCustomerVisitDto> visits;
}
