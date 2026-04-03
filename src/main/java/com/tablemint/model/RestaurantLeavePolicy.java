package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "restaurant_leave_policy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantLeavePolicy {
    @Id
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "days_per_year", nullable = false, precision = 6, scale = 2)
    @Builder.Default
    private BigDecimal daysPerYear = new BigDecimal("12");

    @Column(name = "renew_month", nullable = false)
    @Builder.Default
    private Integer renewMonth = 1;

    @Column(name = "renew_day", nullable = false)
    @Builder.Default
    private Integer renewDay = 1;

    /** At least this many calendar days ⇒ treated as planned leave (admin approval). */
    @Column(name = "planned_leave_min_days", nullable = false)
    @Builder.Default
    private Integer plannedLeaveMinDays = 2;

    /** Casual requests with total days ≤ this value are auto-approved (0 = never auto). */
    @Column(name = "auto_approve_max_days", nullable = false)
    @Builder.Default
    private Integer autoApproveMaxDays = 1;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    @PreUpdate
    void touch() {
        updatedAt = Instant.now();
    }

}
