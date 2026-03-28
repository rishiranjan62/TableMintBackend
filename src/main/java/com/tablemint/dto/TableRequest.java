package com.tablemint.dto;

import com.tablemint.model.TableStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableRequest {

    @NotBlank(message = "Table number is required")
    private String tableNumber;
    @NotBlank(message = "QR code ID is required")
    private String qrCodeId;
    /**
     * Optional. When provided on update, sets the table status (e.g. OCCUPIED when engaged).
     */
    private TableStatus status;
    /**
     * Optional short note for FM/kitchen (e.g. "birthday", "allergy - nut-free").
     */
    private String notes;
}
