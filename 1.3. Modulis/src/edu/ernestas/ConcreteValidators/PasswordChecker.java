package edu.ernestas.ConcreteValidators;

import edu.ernestas.Helpers.ValidationResult;
import edu.ernestas.Helpers.ValidationResultsList;
import edu.ernestas.Helpers.ValidatorHelpers;
import edu.ernestas.Helpers.ValidationAcceptableInputs;
import java.util.List;

public class PasswordChecker implements Validator {
    private static final String MISSING_SPECIAL_SYMBOL = "Password requires at least one special symbol";
    private static final String MISSING_UPPERCASE_SYMBOL = "Password requires at least one uppercase symbol";
    private static final String PASSWORD_TOO_SHORT = "Password is too short";
    private static final String IS_REQUIRED = "Password is required";

    private ValidationResult isPasswordLengthIsValid(String passwordToValidate) {
        String messageAboutPasswordLength = ": password's length should be longer than " + ValidationAcceptableInputs.VALID_PASSWORD_LENGTH;
        if (passwordToValidate.length() >= ValidationAcceptableInputs.VALID_PASSWORD_LENGTH) {
            return new ValidationResult();
        }
        return new ValidationResult(PASSWORD_TOO_SHORT + messageAboutPasswordLength, false);
    }

    private ValidationResult passwordContainsAtLeastOneSpecialSymbol(String passwordToValidate) {
        List<Character> specialSymbolsForPassword = ValidationAcceptableInputs.getSpecialSymbolsForPassword();

        for (char specialSymbolForPassword : specialSymbolsForPassword) {
            if(passwordToValidate.contains(Character.toString(specialSymbolForPassword))) {
                return new ValidationResult();
            }
        }
        return new ValidationResult(MISSING_SPECIAL_SYMBOL, false);
    }

    private ValidationResult passwordContainsAtLeastOneUpperCaseSymbol(String passwordToValidate) {
        if (ValidatorHelpers.containsAtLeastOneUpperCase(passwordToValidate)) {
            return new ValidationResult();
        }
        return new ValidationResult(MISSING_UPPERCASE_SYMBOL, false);
    }

    @Override
    public ValidationResult validate(String passwordToValidate) {
        ValidationResultsList validationResultsList = new ValidationResultsList();

        validationResultsList.addValidationResult(ValidatorHelpers.checkIfStringIsNotNull(passwordToValidate, IS_REQUIRED));
        validationResultsList.addValidationResult(isPasswordLengthIsValid(passwordToValidate));
        validationResultsList.addValidationResult(passwordContainsAtLeastOneUpperCaseSymbol(passwordToValidate));
        validationResultsList.addValidationResult(passwordContainsAtLeastOneSpecialSymbol(passwordToValidate));

        return validationResultsList.getValidationResult();
    }

    @Override
    public boolean isValid(String passwordToValidate) {
        return validate(passwordToValidate).getStatus();
    }
}
