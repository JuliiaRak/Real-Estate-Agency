package com.solvd.service.validators.date;

import com.solvd.service.validators.Validator;

import java.util.Date;

public class FutureDateValidator implements Validator<Date> {
    private final Validator<Date> validator;

    public FutureDateValidator(Validator<Date> validator) {
        this.validator = validator;
    }

    public FutureDateValidator() {
        this.validator = new BasicDateValidator();
    }

    @Override
    public void validate(String fieldName, Date entity) {
        validator.validate(fieldName, entity);
        if (entity.before(new Date())) {
            throw new IllegalArgumentException(String.format("%s must be a future date", fieldName));
        }
    }
}
