package com.solvd.domain.exceptions;

public class EntityAlreadyExistExeption extends Exception {
    public EntityAlreadyExistExeption(String entityName, String fieldName, String name) {
        super(String.format("%s with %s %s already exists", entityName, fieldName, name));
    }
}
