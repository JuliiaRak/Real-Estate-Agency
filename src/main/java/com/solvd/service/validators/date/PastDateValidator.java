package com.solvd.service.validators.date;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;

import java.util.Date;

public class PastDateValidator implements Validator<Date> {
    private final Validator<Date> validator;

    public PastDateValidator(Validator<Date> validator) {
        this.validator = validator;
    }

    public PastDateValidator() {
        this.validator = new BasicDateValidator();
    }

    @Override
    public void validate(String fieldName, Date entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity.after(new Date())) {
            throw new FieldValidationException(String.format("%s must be a past date", fieldName));
        }
    }
}
