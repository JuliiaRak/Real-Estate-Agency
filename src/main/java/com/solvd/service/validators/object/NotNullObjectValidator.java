package com.solvd.service.validators.object;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;

public class NotNullObjectValidator implements Validator<Object> {
    private final Validator<Object> validator;

    public NotNullObjectValidator() {
        this.validator = new BasicObjectValidator();
    }

    public NotNullObjectValidator(Validator<Object> validator) {
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, Object entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity == null) {
            throw new FieldValidationException(String.format("%s cannot be null", fieldName));
        }
    }
}
