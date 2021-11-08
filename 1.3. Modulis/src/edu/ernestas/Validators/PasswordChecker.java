package edu.ernestas.Validators;

import edu.ernestas.Helpers.ValidationResult;

public interface PasswordChecker {

    ValidationResult validatePassword(String passwordToValidate);

    boolean isPasswordValid(String passwordToValidate);
}
