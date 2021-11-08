package edu.ernestas.Validators;

import edu.ernestas.Helpers.ValidationResult;

public interface PhoneValidator {

    ValidationResult validatePhone(String phoneToValidate, String countryCode);

    ValidationResult validatePhone(String phoneToValidate);

    boolean isPhoneValid(String phoneToValidate);

    String transformPhoneShortPrefixToFullPrefix(String phoneToTransform);

    boolean isPhoneValidByCountryCode(String phoneToValidate, String countryCode);
}
