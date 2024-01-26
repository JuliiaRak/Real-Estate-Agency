package com.solvd.domain.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String entityName, String fieldName, String name) {
        super(String.format("%s with %s %s already exists", entityName, fieldName, name));
    }
}
