package com.tablemint.dto;

import com.tablemint.model.StaffTaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class StaffTaskDto {
    private Long id;
    private String title;
    private StaffTaskStatus status;
    private Long assigneeUserId;
    private String assigneeName;
    private Long createdByUserId;
    private String createdByName;
    private Instant createdAt;
    private Instant updatedAt;
}
