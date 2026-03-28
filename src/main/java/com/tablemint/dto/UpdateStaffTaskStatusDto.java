package com.tablemint.dto;

import com.tablemint.model.StaffTaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStaffTaskStatusDto {
    @NotNull
    private StaffTaskStatus status;
}
