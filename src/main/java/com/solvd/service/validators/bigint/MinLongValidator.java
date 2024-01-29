package com.solvd.service.validators.bigint;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;

public class MinLongValidator implements Validator<Long> {
    private final Validator<Long> validator;
    private long bound = 10_000;

    public MinLongValidator() {
        this.validator = new BasicLongValidator();
    }

    public MinLongValidator(Validator<Long> validator) {
        this.validator = validator;
    }

    public MinLongValidator(long bound) {
        this.bound = bound;
        this.validator = new BasicLongValidator();
    }

    public MinLongValidator(long bound, Validator<Long> validator) {
        this.bound = bound;
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, Long entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity < bound) {
            throw new FieldValidationException(String.format("%s cannot be less than %d", fieldName, bound));
        }
    }
}
