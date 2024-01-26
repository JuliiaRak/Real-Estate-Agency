package com.solvd.service.validators;

public interface Validator<T> {
    void validate(String fieldName, T entity);
}
