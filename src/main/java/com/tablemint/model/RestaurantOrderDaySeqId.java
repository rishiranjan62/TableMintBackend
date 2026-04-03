package com.tablemint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOrderDaySeqId implements Serializable {

    private Long restaurantId;
    private LocalDate orderDay;
}
