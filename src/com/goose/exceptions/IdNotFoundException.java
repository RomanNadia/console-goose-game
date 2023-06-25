package com.goose.exceptions;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String id) {
        super(id + " is not found!");
    }
}
