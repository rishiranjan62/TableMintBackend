package com.tablemint.dto;

import com.tablemint.model.StaffStatus;
import com.tablemint.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgRestaurantStaffDto {

    private Long id;
    private String phoneNumber;
    private String email;
    private String name;
    private UserRole role;
    private Boolean enabled;
    private StaffStatus staffStatus;
    private LocalDate joiningDate;
}
