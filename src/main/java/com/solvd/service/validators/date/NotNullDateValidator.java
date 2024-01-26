package com.solvd.service.validators.date;

import com.solvd.service.validators.Validator;

import java.util.Date;

public class NotNullDateValidator implements Validator<Date> {
    private final Validator<Date> validator;

    public NotNullDateValidator(Validator<Date> validator) {
        this.validator = validator;
    }

    public NotNullDateValidator() {
        this.validator = new BasicDateValidator();
    }

    @Override
    public void validate(String fieldName, Date entity) {
        validator.validate(fieldName, entity);
        if (entity == null) {
            throw new NullPointerException(String.format("%s cannot be null", fieldName));
        }
    }
}
