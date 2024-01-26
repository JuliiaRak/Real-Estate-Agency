package com.solvd.service;

import com.solvd.service.validators.Validator;
import com.solvd.service.validators.string.EmailStringValidator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.PhoneNumberStringValidator;

public interface PersonService {
    static void validate(String firstName, String lastName, String email, String phoneNumber) {
        Validator<String> notEmptyStringValidator = new NotEmptyStringValidator(new NotNullStringValidator());
        notEmptyStringValidator.validate("first name", firstName);
        notEmptyStringValidator.validate("last name", lastName);

        Validator<String> emailValidator = new EmailStringValidator(notEmptyStringValidator);
        emailValidator.validate("email", email);

        Validator<String> phoneNumberValidator = new PhoneNumberStringValidator(notEmptyStringValidator);
        phoneNumberValidator.validate("phone number", phoneNumber);
    }
}
