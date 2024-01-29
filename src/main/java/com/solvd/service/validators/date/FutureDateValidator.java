package com.solvd.service.validators.date;

import com.solvd.domain.exceptions.FieldValidationException;
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
    public void validate(String fieldName, Date entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity.before(new Date())) {
            throw new FieldValidationException(String.format("%s must be a future date", fieldName));
        }
    }
}
