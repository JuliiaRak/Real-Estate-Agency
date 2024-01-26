package com.solvd.domain.exceptions;

public class EmailAlreadyExistException extends Exception {
    public EmailAlreadyExistException(String message) {
        super(message);
    }

    public EmailAlreadyExistException(String entityName, String fieldName, String name) {
        super(String.format("%s with %s %s already exists", entityName, fieldName, name));
    }
}
