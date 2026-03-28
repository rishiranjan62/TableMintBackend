package com.tablemint.dto;

import com.tablemint.model.UserRole;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePlatformUserRequest {

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String email;

    /**
     * Must be {@link UserRole# PLATFORM_ADMIN} or {@Link UserRole#PLATFORM_MAINTAINER} when set.
     */
    private UserRole role;

    /**
     * {@Code false} revokes portal access (user remains in DB until deleted)
     */
    private Boolean enabled;
}
