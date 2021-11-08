package edu.ernestas.ConcreteValidators;

import edu.ernestas.Helpers.ValidationAcceptableInputs;
import edu.ernestas.Helpers.ValidationResult;
import edu.ernestas.Helpers.ValidationResultsList;
import edu.ernestas.Helpers.ValidatorHelpers;
import java.util.List;

public class EmailValidator implements Validator {
    private static final String MISSING_ETA = "Email is missing '@' symbol";
    private static final String TOO_MANY_ETAS = "Email contains too many '@' symbols";
    private static final String MISSING_SUFFIX = "Email is missing a suffix";
    private static final String MISSING_DOMAIN = "Email is missing a domain";
    private static final String INCORRECT_SUFFIX = "Email's suffix is incorrect";
    private static final String INCORRECT_DOMAIN = "Email's domain is incorrect";
    private static final String MISSING_LOCAL_PART = "Email misses local part (the part before @)";
    private static final String CONTAINS_INVALID_SYMBOLS = "Email contains invalid symbols";
    private static final String IS_REQUIRED = "Email is required";

    private ValidationResult emailContainsOneEtaSymbol(String emailToValidate) {
        int etaOccurrences = ValidatorHelpers.countSubstringOccurrences(emailToValidate, "@");
        if (etaOccurrences == 1) {
            return new ValidationResult();
        }
        else if (etaOccurrences == 0) {
            return new ValidationResult(MISSING_ETA, false);
        }
        else {
            return new ValidationResult(TOO_MANY_ETAS, false);
        }
    }

    private ValidationResult emailContainsCorrectSuffix(String emailToValidate) {
        List<String> emailSuffixes = ValidationAcceptableInputs.getEmailSuffixes();
        String emailToValidateSuffix = ValidatorHelpers.getSubstringAfterPattern(emailToValidate, ".");

        if (emailToValidateSuffix.isEmpty()) {
            return new ValidationResult(MISSING_SUFFIX, false);
        }
        else if(emailSuffixes.contains("." + emailToValidateSuffix)) {
            return new ValidationResult();
        }
        else {
            return new ValidationResult(INCORRECT_SUFFIX, false);
        }
    }

    private ValidationResult emailContainsCorrectDomain(String emailToValidate) {
        String emailSubstringWithDomainAndSuffix = ValidatorHelpers.getSubstringAfterPattern(emailToValidate, "@");
        String emailSubstringWithDomain = ValidatorHelpers.getSubstringBeforePattern(emailSubstringWithDomainAndSuffix, ".");

        if (emailSubstringWithDomain.isEmpty()) {
            return new ValidationResult(MISSING_DOMAIN, false);
        }

        String emailSubstringWithDomainWithoutFirstAndLastCharacter = emailSubstringWithDomain.substring(1, emailSubstringWithDomain.length() - 1);

        if((ValidatorHelpers.isDigit(emailSubstringWithDomain) || ValidatorHelpers.isLetter(emailSubstringWithDomain))
            && (ValidatorHelpers.countSubstringOccurrences(emailSubstringWithDomainWithoutFirstAndLastCharacter, "_") >= 0)) {
            return new ValidationResult();
        }
        return new ValidationResult(INCORRECT_DOMAIN, false);
    }

    private ValidationResult emailLocalPartOmitsInvalidSymbols(String emailToValidate) {
        List<Character> invalidSymbolsForEmail = ValidationAcceptableInputs.getInvalidSymbolsForEmail();
        String emailLocalPartSubstring = ValidatorHelpers.getSubstringBeforePattern(emailToValidate, "@");

        if (emailLocalPartSubstring.isEmpty()) {
            return new ValidationResult(MISSING_LOCAL_PART, false);
        }
        for (char invalidSymbolForEmail : invalidSymbolsForEmail) {
            if(emailLocalPartSubstring.contains(Character.toString(invalidSymbolForEmail))) {
                return new ValidationResult(CONTAINS_INVALID_SYMBOLS, false);
            }
        }
        return new ValidationResult();
    }

    @Override
    public ValidationResult validate(String emailToValidate) {
        ValidationResultsList validationResultsList = new ValidationResultsList();

        validationResultsList.addValidationResult(ValidatorHelpers.checkIfStringIsNotNull(emailToValidate, IS_REQUIRED));
        validationResultsList.addValidationResult(emailContainsOneEtaSymbol(emailToValidate));
        validationResultsList.addValidationResult(emailContainsCorrectSuffix(emailToValidate));
        validationResultsList.addValidationResult(emailContainsCorrectDomain(emailToValidate));
        validationResultsList.addValidationResult(emailLocalPartOmitsInvalidSymbols(emailToValidate));

        return validationResultsList.getValidationResult();
    }

    @Override
    public boolean isValid(String emailToValidate) {
        return validate(emailToValidate).getStatus();
    }
}
