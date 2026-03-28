package com.tablemint.dto;


import com.tablemint.model.UserRole;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlatformUserDto {
    Long id;
    String phoneNumber;
    String name;
    String email;
    UserRole role;
    boolean enabled;
}
