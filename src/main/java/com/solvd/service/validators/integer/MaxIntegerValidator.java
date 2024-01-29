package com.solvd.service.validators.integer;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;

public class MaxIntegerValidator implements Validator<Integer> {
    private final Validator<Integer> validator;
    private int bound = 1_000_000_000;

    public MaxIntegerValidator() {
        this.validator = new BasicIntegerValidator();
    }

    public MaxIntegerValidator(Validator<Integer> validator) {
        this.validator = validator;
    }

    public MaxIntegerValidator(int bound) {
        this.bound = bound;
        this.validator = new BasicIntegerValidator();
    }

    public MaxIntegerValidator(int bound, Validator<Integer> validator) {
        this.bound = bound;
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, Integer entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity > bound) {
            throw new FieldValidationException(String.format("%s cannot be more than %d", fieldName, bound));
        }
    }
}
