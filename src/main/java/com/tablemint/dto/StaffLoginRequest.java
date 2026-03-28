package com.tablemint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffLoginRequest {

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "password is required")
    private String password;
}
