package com.tablemint.dto;

import com.tablemint.model.ShiftKind;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateWorkShiftRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime sendTime;

    @Min(0)
    @Max(240)
    private Integer graceMinutesLate = 15;

    @Min(0)
    @Max(9999)
    private Integer sortOrder = 0;

    /**
     * Defaults to CUSTOM when omitted
     */
    private ShiftKind shiftKind;

}
