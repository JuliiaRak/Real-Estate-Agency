package com.solvd.domain.exceptions;

public class EmailAlreadyExistException extends Exception {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
