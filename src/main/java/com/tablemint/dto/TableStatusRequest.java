package com.tablemint.dto;

import com.tablemint.model.TableStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableStatusRequest {
    @NotNull(message = "Status is required")
    private TableStatus status;
    /**
     * Optional message to show customer when marking table available
     */
    private String customerMessage;
}
