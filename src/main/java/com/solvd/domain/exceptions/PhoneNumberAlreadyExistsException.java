package com.solvd.domain.exceptions;

public class PhoneNumberAlreadyExistsException extends Exception {
    public PhoneNumberAlreadyExistsException(String message) {
        super(message);
    }

    public PhoneNumberAlreadyExistsException(String employee, String phoneNumber, String phoneNumber1) {
        super(String.format("%s with %s %s already exists", employee, phoneNumber, phoneNumber1));
    }
}
