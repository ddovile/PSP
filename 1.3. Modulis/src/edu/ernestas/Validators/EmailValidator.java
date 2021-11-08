package edu.ernestas.Validators;

import edu.ernestas.Helpers.ValidationResult;

public interface EmailValidator {

    ValidationResult validateEmail(String emailToValidate);

    boolean isEmailValid(String emailToValidate);
}
