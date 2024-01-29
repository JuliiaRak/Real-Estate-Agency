package com.solvd.service.validators.integer;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;

public class NotNegativeIntegerValidator implements Validator<Integer> {
    private final Validator<Integer> validator;

    public NotNegativeIntegerValidator() {
        this.validator = new BasicIntegerValidator();
    }

    public NotNegativeIntegerValidator(Validator<Integer> validator) {
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, Integer entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity < 0) {
            throw new FieldValidationException(String.format("%s must not be negative", fieldName));
        }
    }
}
