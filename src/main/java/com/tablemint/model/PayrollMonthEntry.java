package com.tablemint.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        name = "payroll_month_entry",
        uniqueConstraints =
        @UniqueConstraint(
                name = "uq_payroll_month_user",
                columnNames = {"restaurant_id", "user_id", "period_year", "period_month"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollMonthEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "period_year", nullable = false)
    private int periodYear;

    @Column(name = "period_month", nullable = false)
    private int periodMonth;

    @Column(name = "amount_paid", nullable = false, precision = 12, scale = 2)
    private BigDecimal amountPaid;

    @Column(name = "attendance_hours", precision = 10, scale = 2)
    private BigDecimal attendanceHours;

    @Column(name = "paid_at", nullable = false)
    private Instant paidAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        Instant n = Instant.now();
        if (createdAt == null) {
            createdAt = n;
        }
        if (updatedAt == null) {
            updatedAt = n;
        }
        if (paidAt == null) {
            paidAt = n;
        }
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

}
