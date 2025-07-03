package com.phoneshop.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
