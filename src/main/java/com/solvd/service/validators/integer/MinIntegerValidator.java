package com.solvd.service.validators.integer;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;

public class MinIntegerValidator implements Validator<Integer> {
    private final Validator<Integer> validator;
    private int bound = 100;

    public MinIntegerValidator() {
        this.validator = new BasicIntegerValidator();
    }

    public MinIntegerValidator(Validator<Integer> validator) {
        this.validator = validator;
    }

    public MinIntegerValidator(int bound) {
        this.bound = bound;
        this.validator = new BasicIntegerValidator();
    }

    public MinIntegerValidator(int bound, Validator<Integer> validator) {
        this.bound = bound;
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, Integer entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity < bound) {
            throw new FieldValidationException(String.format("%s cannot be less than %d", fieldName, bound));
        }
    }
}
