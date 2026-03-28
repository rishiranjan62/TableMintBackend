package com.tablemint.dto;

import com.tablemint.model.ShiftKind;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalTime;

@Data
public class UpdateWorkShiftRequest {

    @Size(max = 100)
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    @Min(0)
    @Max(240)
    private Integer graceMinutesLate;
    @Min(0)
    @Max(9999)
    private Integer sortOrder;
    private Boolean active;
    private ShiftKind shiftkind;
}
