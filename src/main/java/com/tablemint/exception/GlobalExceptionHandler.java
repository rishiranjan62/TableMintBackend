package com.tablemint.exception;

import com.tablemint.config.ActivityLog;
import com.tablemint.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        ActivityLog.setActivity("Error: Resource not found", ex.getMessage());
        log.warn("Resource not found: {}", ex.getMessage());
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleForbidden(
            ForbiddenException ex,
            HttpServletRequest request) {
        ActivityLog.setActivity("Error: Forbidden", ex.getMessage());
        log.warn("Forbidden: {}", ex.getMessage());
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.FORBIDDEN.value())
                .error("Forbidden")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflict(
            ConflictException ex,
            HttpServletRequest request) {
        ActivityLog.setActivity("Error: Conflict", ex.getMessage());
        log.warn("Conflict: {}", ex.getMessage());
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(TableUnavailableException.class)
    public ResponseEntity<ApiError> handleTableUnavailable(
            TableUnavailableException ex,
            HttpServletRequest request) {
        ActivityLog.setActivity("Error: Table unavailable", ex.getMessage());
        log.warn("Table unavailable: {}", ex.getMessage());
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .customerMessage(ex.getCustomerMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        ActivityLog.setActivity("Error: Validation failed", ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + "=" + fe.getDefaultMessage())
                .collect(Collectors.joining("; ")));
        List<ApiError.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> ApiError.FieldError.builder()
                        .field(fe.getField())
                        .message(fe.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Invalid request body")
                .path(request.getRequestURI())
                .fieldErrors(fieldErrors)
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {
        String param = ex.getName();
        Class<?> requiredType = ex.getRequiredType() != null ? ex.getRequiredType() : Object.class;
        String value = ex.getValue() != null ? ex.getValue().toString() : "null";
        String requiredTypeName = requiredType.getSimpleName();

        String message = String.format(
                "Invalid path parameter '%s': expected %s, got '%s'.",
                param, requiredTypeName, value);

        if (request.getRequestURI() != null && request.getRequestURI().contains("/tables/")
                && Number.class.isAssignableFrom(requiredType)) {
            message += " For lookup by QR code use GET /api/v1/tables/qr/{qrCodeId}.";
        }

        ActivityLog.setActivity("Error: Type mismatch", message);
        log.warn("Type mismatch: {}", message);
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(message)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(
            IllegalArgumentException ex, HttpServletRequest request) {
        ActivityLog.setActivity("Error: Bad request", ex.getMessage());
        log.warn("Bad request: {}", ex.getMessage());
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest request) {
        String msg = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining("; "));
        ActivityLog.setActivity("Error: Validation", msg);
        log.warn("Constraint violation: {}", msg);
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message(msg.isEmpty() ? "Invalid parameters" : msg)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiError> handleBind(BindException ex, HttpServletRequest request) {
        ActivityLog.setActivity("Error: Bind validation", ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + "=" + fe.getDefaultMessage())
                .collect(Collectors.joining("; ")));
        List<ApiError.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> ApiError.FieldError.builder()
                        .field(fe.getField())
                        .message(fe.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Invalid request")
                .path(request.getRequestURI())
                .fieldErrors(fieldErrors)
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
        ActivityLog.setActivity("Error: Unhandled", ex.getClass().getSimpleName() + ": " + ex.getMessage());
        log.error("Unhandled error", ex);
        ApiError error = ApiError.builder()
                .timestamp(java.time.Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An unexpected error occurred")
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
