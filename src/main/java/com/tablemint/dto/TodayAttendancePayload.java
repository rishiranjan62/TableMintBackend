package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodayAttendancePayload {

    private StaffAttendanceResponse record;

    private Long suggestedShiftId;

}
