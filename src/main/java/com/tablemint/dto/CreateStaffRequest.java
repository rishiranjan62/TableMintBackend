package com.tablemint.dto;

import com.tablemint.model.UserRole;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateStaffRequest {

    @NotBlank(message = "Phone number is required")
    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 255)
    private String email;

    @Size(max = 100)
    private String name;

    @NotNull(message = "Role is required")
    private UserRole role;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 30)
    private String password;


    @PastOrPresent(message = "Joining date can't be in future")
    private LocalDate joiningDate;


    @DecimalMin(value = "0.0", inclusive = true, message = "salary can't be negative")
    @Digits(integer = 10, fraction = 2, message = "Salary must have at most 10 integer digits and 2 decimal places")
    private BigDecimal salary;

}
