package com.solvd.service.validators.number;

import com.solvd.service.validators.Validator;

public class NotNegativeLongValidator implements Validator<Long> {
    private final Validator<Long> validator;

    public NotNegativeLongValidator() {
        this.validator = new BasicLongValidator();
    }

    public NotNegativeLongValidator(Validator<Long> validator) {
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, Long entity) {
        validator.validate(fieldName, entity);
        if (entity < 0) {
            throw new IllegalArgumentException(String.format("%s must not be negative", fieldName));
        }
    }
}
