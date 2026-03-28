package com.tablemint.dto;

import com.tablemint.model.ShiftKind;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeeklyAssignmentDto {
    private Long userId;
    private String dayOfWeek;
    private Long workShiftId;
    private String shiftName;
    private ShiftKind shiftkind;
    private Boolean overnight;

}
