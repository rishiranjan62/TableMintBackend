package com.tablemint.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "staff_leave_balance", uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "user_id", "cycle_start"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffLeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "cycle_start", nullable = false)
    private LocalDate cycleStart;

    @Column(name = "cycle_end", nullable = false)
    private LocalDate cycleEnd;

    @Column(name = "days_allocated", nullable = false, precision = 6, scale = 2)
    private BigDecimal daysAllocated;

    @Column(name = "days_used", nullable = false, precision = 6, scale = 2)
    @Builder.Default
    private BigDecimal daysUsed = BigDecimal.ZERO;

    @Column(name = "days_pending", nullable = false, precision = 6, scale = 2)
    @Builder.Default
    private BigDecimal daysPending = BigDecimal.ZERO;

}
