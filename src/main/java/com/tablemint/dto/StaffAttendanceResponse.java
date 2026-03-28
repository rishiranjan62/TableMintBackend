package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class StaffAttendanceResponse {
    private Long id;
    private Long userId;
    /**
     * Present for team views.
     */
    private String staffName;
    private String staffPhone;
    private LocalDate workDate;
    private Long shiftId;

    private String shiftName;
    /**
     * Team log only: true when f@link #shiftId}/{@link #shiftName} come from the weekly template because the row had
     * no shift stored at clock-in (Legacy rows). Late/early are computed from this effective shift.
     */
    private Boolean shiftFromSchedule;
    private Instant clockInAt;
    private Instant clockOutAt;
    private
    Boolean lateArrival;
    private Integer lateMinutes;
    /**
     * True when clock-out is before scheduled shift end (same-day shifts; overnight shifts use next-day end).
     */
    private Boolean earlyLogout;
    private Integer earlyLogoutMinutes;
    /**
     * True when clocked in but not yet out.
     */
    private Boolean open;
}
