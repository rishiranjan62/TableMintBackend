package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChefPerformanceSummaryDto {

    private Long chefUserId;
    private String chefName;
    private long lineItemsPrepared;
    private long distinctOrders;
}
