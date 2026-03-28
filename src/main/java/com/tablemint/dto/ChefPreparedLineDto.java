package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ChefPreparedLineDto {

    private Long orderItemId;
    private Long orderId;
    private String tableNumber;
    private Long tableId;
    private String menuItemName;
    private int quantity;
    private Long chefUserId;
    private String chefName;
    private Instant preparedAt;

}
