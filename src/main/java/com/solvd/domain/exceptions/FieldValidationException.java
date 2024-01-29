package com.solvd.domain.exceptions;

public class FieldValidationException extends Exception {
    public FieldValidationException(String message) {
        super(message);
    }

    public FieldValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
