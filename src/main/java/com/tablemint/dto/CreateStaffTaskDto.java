package com.tablemint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStaffTaskDto {

    @NotBlank
    @Size(max = 512)
    private String title;

    @NotNull
    private Long assigneeUserId;

}
