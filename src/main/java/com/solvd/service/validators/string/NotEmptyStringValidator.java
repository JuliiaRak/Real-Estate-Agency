package com.solvd.service.validators.string;

import com.solvd.service.validators.Validator;

public class NotEmptyStringValidator implements Validator<String> {
    private final Validator<String> validator;

    public NotEmptyStringValidator() {
        this.validator = new BasicStringValidator();
    }

    public NotEmptyStringValidator(Validator<String> validator) {
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, String entity) {
        validator.validate(fieldName, entity);
        if (entity.matches("\\s*")) {
            throw new IllegalArgumentException(String.format("Specify %s", fieldName));
        }
    }
}
