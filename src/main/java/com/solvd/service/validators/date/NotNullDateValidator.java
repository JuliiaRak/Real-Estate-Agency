package com.solvd.service.validators.date;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.object.NotNullObjectValidator;

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
    public void validate(String fieldName, Date entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        new NotNullObjectValidator().validate(fieldName, entity);
    }
}
