package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class WeeklyScheduleResponse {
    /**
     * Monday of the displayed week (ISO).
     */
    private LocalDate weekStart;
    private List<LocalDate> weekDates;
    private List<WeeklyScheduleStaffDto> staff;
    private List<WorkShiftResponse> shifts;
    private List<WeeklyAssignmentDto> assignments;
}
