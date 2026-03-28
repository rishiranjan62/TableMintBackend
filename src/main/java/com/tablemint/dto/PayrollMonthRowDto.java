package com.tablemint.dto;

import com.tablemint.model.UserRole;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class PayrollMonthRowDto {

    private Long userId;
    private String name;
    private UserRole role;
    /**
     * Configured monthly salary on staff profile (may be null).
     */
    private BigDecimal monthlySalary;
    /**
     * Clocked hours overlapping this calendar month (Asia/Kolkata dates).
     */
    private double attendanceHours;
    /**
     * Standard month hours for pro-rata (8h × 22 working days).
     */
    private int expectedHours;
    /**
     * •monthly salary * min(1, attendanceHours / expectedHours, or null when no monthly salary is set on
     * (no automatic calculation).
     */
    private BigDecimal calculatedPay;
    private PayrollPaymentStatus status;
    /**
     * When marked paid (record created).
     */
    private Instant paidAt;
    /**
     * Snapshot amount stored when paid.
     */
    private BigDecimal amountPaid;

}
