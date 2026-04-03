package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Links a customer (user) to a table. One active occupancy per user and per table.
 * leftAt null = currently at table.
 */
@Entity
@Table(name = "table_occupancy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableOccupancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable restaurantTable;

    @Column(name = "joined_at", nullable = false, updatable = false)
    private Instant joinedAt;

    @Column(name = "left_at")
    private Instant leftAt;

    @PrePersist
    void prePersist() {
        if (joinedAt == null) joinedAt = Instant.now();
    }

}
