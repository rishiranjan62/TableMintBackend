package com.tablemint.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BulkWeeklyAssignmentRequest {

    @NotNull
    private Long userId;

    /**  When null , clears assignments for every day in {@Link # dayOfWeeks}.   */
    private Long workShiftId;

    /** Each Value : MONDAY .... SUNDAY (Case-insensitive) */
    @NotEmpty
    private List<String> dayOfWeeks;

}
