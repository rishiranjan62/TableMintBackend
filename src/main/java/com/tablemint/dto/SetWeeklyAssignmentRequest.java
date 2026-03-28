package com.tablemint.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SetWeeklyAssignmentRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String dayOfWeek;

    /**
     * When null, clears the assignment for that cell.
     */
    private Long workShiftId;

}
