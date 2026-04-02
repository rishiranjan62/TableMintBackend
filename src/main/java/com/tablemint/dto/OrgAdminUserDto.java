package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrgAdminUserDto {

    private Long id;
    private String phoneNumber;
    private String name;
    private Boolean enabled;
    private String email;
}
