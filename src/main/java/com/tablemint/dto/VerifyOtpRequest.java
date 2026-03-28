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
public class VerifyOtpRequest {

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]? [0-9]{10,15}$", message = "Invalid phone number")
    private String phoneNumber;
    @NotBlank(message = "OTP is required")
    @Pattern(regexp = "^[0-9]{4,8}$", message = "OTP must be 4-8 digits")
    private String otp;
    /**
     * Must match the scope used when sending OTP (same QR / table as login flow).
     */
    private String grCodeId;
    private Long tableId;
    /**
     * Must match send OTP (same restaurant scope as
     * QR / table).
     */
    private Long restaurantId;
}
