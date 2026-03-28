package com.tablemint.dto;

import com.tablemint.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePlatformUserRequest {

    @NotBlank
    @Size(max = 20)
    private String phoneNumber;

    @NotBlank
    @Size(min = 6,max = 30)
    private String password;

    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String email;

    /** Must be {@Link UserRole#PLATFORM_ADMIN} or {@Link UserRole#PLATFORM_MAINTAINER}*/
    @NotNull
    private UserRole role;

}
