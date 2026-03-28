package com.tablemint.dto;

import com.tablemint.model.AuditAction;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AuditLogEntry {

    private Long id;
    private Instant createdAt;
    private AuditAction action;
    private String entityType;
    private Long entityId;
    private Long userId;
    private String details;
}
