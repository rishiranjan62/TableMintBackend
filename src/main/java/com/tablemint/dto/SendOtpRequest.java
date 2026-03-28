package com.tablemint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendOtpRequest {

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number")
    private String phoneNumber;
    /**
     * Optional: table number when customer is trying to join a table (shown to FM/Admin with 0TP).
     */
    private String tableNumber;
    /**
     * QR code id from scanned table - scopes OTto this restaurant (required for multi-tenant join).
     */
    private String qrCodeId;
    /**
     * Alternative: numeric table id to resolve restaurant when QR not available.
     */
    private Long tableId;
    /**
     * When the scan URL includes the venue (e.g. qr=T1 and restaurantId=5), OTP and join resolve the table
     * within that restaurant. Required when the same table label or short code exists at multiple venues.
     */
    private Long restaurantId;
}
