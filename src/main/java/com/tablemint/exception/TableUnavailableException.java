package com.tablemint.exception;

import lombok.Getter;

@Getter
public class TableUnavailableException extends RuntimeException {

    private final String customerMessage;


    public TableUnavailableException(String message, String customerMessage) {
        super(message);
        this.customerMessage = customerMessage;
    }
}
