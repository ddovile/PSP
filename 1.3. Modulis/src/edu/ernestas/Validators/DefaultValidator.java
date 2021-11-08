package edu.ernestas.Validators;

import edu.ernestas.Helpers.ValidationResult;

public class DefaultValidator implements Validator {
    private EmailValidator emailValidator;
    private PasswordChecker passwordChecker;
    private PhoneValidator phoneValidator;

    public DefaultValidator(EmailValidator emailValidator, PasswordChecker passwordChecker, PhoneValidator phoneValidator) {
        this.emailValidator = emailValidator;
        this.passwordChecker = passwordChecker;
        this.phoneValidator = phoneValidator;
    }

    @Override
    public ValidationResult validateEmail(String emailToValidate) {
        return emailValidator.validateEmail(emailToValidate);
    }

    @Override
    public boolean isEmailValid(String emailToValidate) {
        return emailValidator.isEmailValid(emailToValidate);
    }

    @Override
    public ValidationResult validatePassword(String passwordToValidate) {
        return passwordChecker.validatePassword(passwordToValidate);
    }

    @Override
    public boolean isPasswordValid(String passwordToValidate) {
        return passwordChecker.isPasswordValid(passwordToValidate);
    }

    @Override
    public ValidationResult validatePhone(String phoneToValidate, String countryCode) {
        return phoneValidator.validatePhone(phoneToValidate, countryCode);
    }

    @Override
    public ValidationResult validatePhone(String phoneToValidate) {
        return phoneValidator.validatePhone(phoneToValidate);
    }

    @Override
    public boolean isPhoneValid(String phoneToValidate) {
        return phoneValidator.isPhoneValid(phoneToValidate);
    }

    @Override
    public String transformPhoneShortPrefixToFullPrefix(String phoneToTransform) {
        return phoneValidator.transformPhoneShortPrefixToFullPrefix(phoneToTransform);
    }

    @Override
    public boolean isPhoneValidByCountryCode(String phoneToValidate, String countryCode) {
        return phoneValidator.isPhoneValidByCountryCode(phoneToValidate, countryCode);
    }
}