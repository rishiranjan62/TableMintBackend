package com.tablemint.dto;

import com.tablemint.service.StaffService;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStaffStatusRequest {
    @NotNull(message = "status is required")
    private StaffService status;
}
