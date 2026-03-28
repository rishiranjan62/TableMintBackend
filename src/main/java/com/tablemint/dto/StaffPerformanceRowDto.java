package com.tablemint.dto;

import com.tablemint.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffPerformanceRowDto {
    private Long userId;
    private String name;
    private UserRole role;

    /**
     * Distinct orders credited in range: served as assigned waiter, or kitchen-ready ticket where the user was
     * order chef or had at least one prepared line on that ticket.
     */
    private int ordersHandled;
    /**
     * Average seconds from kitchen ready to served for orders this user served as waiter in range;
     * null when there is no such order or timestamps are missing.
     */
    private Double avgServiceTimeSeconds;
    /**
     * Clocked time overlapping the report window hours).
     */
    private double activeHours;
    /**
     * Staff tasks marked DONE with {@code updatedAt) in range.
     */
    private int tasksCompleted;

}
