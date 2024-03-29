package com.solvd.domain.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String name, long id) {
        super(name + " with id " + id + " not found");
    }

    public EntityNotFoundException(String name, String fieldName) {
        super(name + " have not been found by " + fieldName);
    }
}
