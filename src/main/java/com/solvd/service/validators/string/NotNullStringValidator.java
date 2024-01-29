package com.solvd.service.validators.string;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.object.NotNullObjectValidator;

public class NotNullStringValidator implements Validator<String> {
    private final Validator<String> validator;

    public NotNullStringValidator() {
        this.validator = new BasicStringValidator();
    }

    public NotNullStringValidator(Validator<String> validator) {
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, String entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        new NotNullObjectValidator().validate(fieldName, entity);
    }
}
