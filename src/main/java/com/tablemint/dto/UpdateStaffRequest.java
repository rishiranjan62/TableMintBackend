package com.tablemint.dto;

import com.tablemint.model.UserRole;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpdateStaffRequest {
    @Size(max = 20)
    private String phoneNumber;
    @Size(max = 100)
    private String name;
    @Size(max = 255)
    private String email;
    private UserRole role;

    /**
     * If provided, updates the staff member's password.
     */
    @Size(min = 6, max = 100)
    private String password;
    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Salary must have at most 10 integer digits and 2 decimal places")
    private BigDecimal salary;
    /**
     * Employment status: true = active (can log in), false = inactive.
     * Omitted = Leave unchanged.
     */
    private Boolean enabled;
}
