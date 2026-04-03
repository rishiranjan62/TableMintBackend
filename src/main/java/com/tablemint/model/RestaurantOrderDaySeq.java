package com.tablemint.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "restaurant_order_day_seq")
@IdClass(RestaurantOrderDaySeqId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOrderDaySeq {
    @Id
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Id
    @Column(name = "order_day", nullable = false)
    private LocalDate orderDay;

    @Column(name = "last_seq", nullable = false)
    private int lastSeq;

}
