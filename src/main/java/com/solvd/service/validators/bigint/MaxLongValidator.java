package com.solvd.service.validators.bigint;

import com.solvd.service.validators.Validator;

public class MaxLongValidator implements Validator<Long> {
    private final Validator<Long> validator;
    private long bound = 10_000_000_000L;

    public MaxLongValidator() {
        this.validator = new BasicLongValidator();
    }

    public MaxLongValidator(Validator<Long> validator) {
        this.validator = validator;
    }

    public MaxLongValidator(long bound) {
        this.bound = bound;
        this.validator = new BasicLongValidator();
    }

    public MaxLongValidator(long bound, Validator<Long> validator) {
        this.bound = bound;
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, Long entity) {
        validator.validate(fieldName, entity);
        if (entity > bound) {
            throw new IllegalArgumentException(String.format("%s cannot be more than %d", fieldName, bound));
        }
    }
}
