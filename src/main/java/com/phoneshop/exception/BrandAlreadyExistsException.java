package com.phoneshop.exception;

public class BrandAlreadyExistsException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BrandAlreadyExistsException(String message) {
        super(message);
    }
}
