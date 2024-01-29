package com.solvd.service.validators.string;

import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.service.validators.Validator;

public class SizeStringValidator implements Validator<String> {
    private final Validator<String> validator;
    private int size = 50;

    public SizeStringValidator() {
        this.validator = new BasicStringValidator();
    }

    public SizeStringValidator(Validator<String> validator) {
        this.validator = validator;
    }

    public SizeStringValidator(int size) {
        this.size = size;
        this.validator = new BasicStringValidator();
    }

    public SizeStringValidator(int size, Validator<String> validator) {
        this.size = size;
        this.validator = validator;
    }

    @Override
    public void validate(String fieldName, String entity) throws FieldValidationException {
        validator.validate(fieldName, entity);
        if (entity.length() > size) {
            throw new FieldValidationException(String.format("%s is too long. Maximum size is %d", fieldName, size));
        }
    }
}
