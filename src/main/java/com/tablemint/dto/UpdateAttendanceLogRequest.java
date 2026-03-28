package com.tablemint.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UpdateAttendanceLogRequest {

    private Instant clockInAt;
    private Instant clockOutAt;
    /** When true, removes clock-out so the staff member can clock out again(mistaken clock-out).*/
    private Boolean clearClockOut;
}
