package com.tablemint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantSignupRequest {
    @NotBlank(message = "Restaurant name is required")
    @Size(max = 200)
    private String name;

    @NotBlank(message = "Phone Number is required")
    @Size(max = 200)
    private String phone;

    @Size(max = 255)
    private String email;

    @Size(max = 100)
    private String city;

    /**
     * Primary Admin display name : if omitted, restaurant nae is used for the first staff profile .
     */
    @Size(max = 100)
    private String primaryAdminName;


    /**
     * Primary Admin joining date; default to today if omitted .
     */
    @PastOrPresent(message = "Joining date can't be in the future")
    private String primaryAdminJoiningDate;

}
