package com.phoneshop.exception;


public class EntityExistException extends RuntimeException {
    public EntityExistException(String msg) {
        super(msg);
    }
}
