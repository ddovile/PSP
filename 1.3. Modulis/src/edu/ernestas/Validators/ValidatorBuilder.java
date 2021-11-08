package edu.ernestas.Validators;

public class ValidatorBuilder {
    private EmailValidator emailValidator;
    private PasswordChecker passwordChecker;
    private PhoneValidator phoneValidator;

    public ValidatorBuilder() {
        emailValidator = new DefaultEmailValidator();
        passwordChecker = new DefaultPasswordChecker();
        phoneValidator = new DefaultPhoneValidator();
    }

    public ValidatorBuilder setEmailValidator(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
        return this;
    }

    public ValidatorBuilder setPasswordChecker(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
        return this;
    }

    public ValidatorBuilder setPhoneValidator(PhoneValidator phoneValidator) {
        this.phoneValidator = phoneValidator;
        return this;
    }

    public Validator build() {
        return new DefaultValidator(emailValidator, passwordChecker, phoneValidator);
    }
}