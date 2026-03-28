package com.tablemint.dto;

import com.tablemint.model.ShiftKind;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class WorkShiftResponse {
    private Long id;
    private String name;
    private ShiftKind shiftkind;
    /** True when end time is on the next calendar day (e.g. 22:00-06:00). */
    private Boolean overnight;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer graceMinutesLate;
    private Integer sortOrder;
    private Boolean active;
}
