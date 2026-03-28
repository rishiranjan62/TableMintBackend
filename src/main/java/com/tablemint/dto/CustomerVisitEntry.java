package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerVisitEntry {

    private Long userId;
    private String phoneNumber;
    private String name;
    /**
     * Number of visits (table occupancies ended ) in the requested date range.
     */
    private long visitCount;
}
