package edu.ernestas.ConcreteValidators;

import edu.ernestas.Helpers.*;
import java.util.List;

public class PhoneValidator implements Validator {
    private static final String INVALID_PREFIX = "Phone prefix is incorrect";
    private static final String INVALID_FORMAT = "Phone format is incorrect, it should contain only numbers with an optional preceding + sign";
    private static final String MISSING_VALIDATION_RULES_FOR_COUNTRY = "Phone prefixes / lengths by this country are missing or are incorrect";
    private static final String INVALID_LENGTH = "Phone length is incorrect";
    private static final String IS_REQUIRED = "Phone is required";
    private static final String CHANGE_LT_PREFIX_FROM = "8";
    private static final String CHANGE_LT_PREFIX_TO = "+370";

    private ValidationResult isPhoneValidAccordingToPhoneValidationRules(String phoneToValidate, PhoneValidationRules phoneValidationRules) {
        if(phoneValidationRules == null) {
            return new ValidationResult(MISSING_VALIDATION_RULES_FOR_COUNTRY, false);
        }
        for (String prefix : phoneValidationRules.getPhonePrefixes()) {
            String phoneNumberPrefix = phoneToValidate.substring(0, prefix.length());
            String phoneNumberWithoutPrefix = phoneToValidate.substring(prefix.length());

            if (phoneNumberPrefix.equals(prefix)) {
                if (phoneValidationRules.getPhoneLengths().contains(phoneNumberWithoutPrefix.length())) {
                    return new ValidationResult();
                }
                else {
                    return new ValidationResult(INVALID_LENGTH, false);
                }
            }
        }
        return new ValidationResult(INVALID_PREFIX, false);
    }

    private ValidationResult isPhoneFormatValid(String phoneToValidate) {
        if (!ValidatorHelpers.isDigit(phoneToValidate) &&
            !(phoneToValidate.charAt(0) == '+' && ValidatorHelpers.isDigit(phoneToValidate.substring(1)))) {
            return new ValidationResult(INVALID_FORMAT, false);
        }
        return new ValidationResult();
    }

    public ValidationResult validate(String phoneToValidate, String countryCode) {
        ValidationResultsList validationResultsList = new ValidationResultsList();
        PhoneValidationRules phoneValidationRules = ValidationAcceptableInputs.getPhoneValidationRulesByCountry(countryCode);

        validationResultsList.addValidationResult(validate(phoneToValidate));
        validationResultsList.addValidationResult(isPhoneValidAccordingToPhoneValidationRules(phoneToValidate, phoneValidationRules));

        return validationResultsList.getValidationResult();
    }

    @Override
    public ValidationResult validate(String phoneToValidate) {
        ValidationResultsList validationResultsList = new ValidationResultsList();

        validationResultsList.addValidationResult(ValidatorHelpers.checkIfStringIsNotNull(phoneToValidate, IS_REQUIRED));
        validationResultsList.addValidationResult(isPhoneFormatValid(phoneToValidate));

        return validationResultsList.getValidationResult();
    }

    @Override
    public boolean isValid(String phoneToValidate) {
        return validate(phoneToValidate).getStatus();
    }

    public boolean isValidByCountryCode(String phoneToValidate, String countryCode) {
        return validate(phoneToValidate, countryCode).getStatus();
    }

    public String convertPhoneNumber(String phoneToTransform) {
        String countryCode = "LT";
        String changePrefixFrom = CHANGE_LT_PREFIX_FROM;
        String changePrefixTo = CHANGE_LT_PREFIX_TO;
        List<String> phonePrefixesByCountry = ValidationAcceptableInputs.getPhoneValidationRulesByCountry(countryCode).getPhonePrefixes();

        if (phonePrefixesByCountry.contains(changePrefixFrom) && phonePrefixesByCountry.contains(changePrefixTo) && phoneToTransform.startsWith(changePrefixFrom)) {
            String phoneWithoutPrefix = phoneToTransform.substring(changePrefixFrom.length());
            return changePrefixTo + phoneWithoutPrefix;
        }
        return phoneToTransform;
    }
}