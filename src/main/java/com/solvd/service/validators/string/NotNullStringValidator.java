package com.solvd.service.validators.string;

import com.solvd.service.validators.Validator;

public class NotNullStringValidator implements Validator<String> {
    private final Validator<String> validator;

    public NotNullStringValidator() {
        this.validator = new BasicStringValidator();
    }

    public NotNullStringValidator(Validator<String> validator) {
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, String entity) {
        validator.validate(fieldName, entity);
        if (entity == null) {
            throw new NullPointerException(String.format("%s cannot be null", fieldName));
        }
    }
}
