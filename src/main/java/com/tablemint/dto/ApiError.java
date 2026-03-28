package com.tablemint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<FieldError> fieldErrors;
    /** Optional message for customer when table was marked available (409). */
    private String customerMessage;

    @Data
    @Builder
    public static class FieldError {
        private String field;
        private String message;
    }

}
