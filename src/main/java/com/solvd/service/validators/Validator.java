package com.solvd.service.validators;

import com.solvd.domain.exceptions.FieldValidationException;

public interface Validator<T> {
    void validate(String fieldName, T entity) throws FieldValidationException;
}
