package com.tablemint.dto;

import com.tablemint.model.ShiftKind;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePresetShiftRequest {
    @NotNull
    private ShiftKind kind;
}
