package com.tablemint.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }

    public static HttpStatus status() {
        return HttpStatus.FORBIDDEN;
    }
}
