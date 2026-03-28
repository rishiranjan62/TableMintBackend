package com.tablemint.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingResponse {
    private Long restaurantId; private String restaurantName;
    private
    String slug;
    /** Phone number to use for staff login */
    private String LoginPhone;
    /** Generated temporary password; user should change after first login */
    private String generatedPassword;
    /** FulL URL to staff login (e.g.<a href="https://app.example.com/staff/login">...</a>) */
    private String staffLoginUrL;
}
