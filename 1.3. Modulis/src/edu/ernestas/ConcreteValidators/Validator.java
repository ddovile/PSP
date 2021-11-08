package edu.ernestas.ConcreteValidators;

import edu.ernestas.Helpers.ValidationResult;

public interface Validator {

    ValidationResult validate(String inputToValidate);

    boolean isValid(String inputToValidate);
}
