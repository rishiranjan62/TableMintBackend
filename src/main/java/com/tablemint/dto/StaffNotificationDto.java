package com.tablemint.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class StaffNotificationDto {
    private Long id;
    private String kind;
    private String title;
    private String body;
    private Instant readAt;
    private Long relatedLeaveRequestId;
    /**
     * Deep Link in staff app (e.g. /staff/inventory).
     */
    private String LinkPath;
    private Instant createdAt;
}
