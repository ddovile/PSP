package edu.ernestas.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationAcceptableInputs {
    public static final int VALID_PASSWORD_LENGTH = 10;
    private static final List<String> emailSuffixes = new ArrayList<>() {{
        add(".com");
        add(".net");
        add(".org");
        add(".edu");
        add(".lt");
    }};
    private static final List<Character> invalidSymbolsForEmail = new ArrayList<>() {{
        add('#');//should be removed
        add('.');
        add('(');
        add(')');
        add('[');
        add(']');
        add('\\');
        add('"');
        add(':');
        add(';');
        add('<');
        add('>');
    }};
    private static final List<Character> specialSymbolsForPassword = new ArrayList<>() {{
        add('(');
        add(')');
        add('{');
        add('}');
        add('[');
        add(']');
        add('/');
        add('\\');
        add('\'');
        add('"');
        add('.');
        add('?');
        add('!');
        add('#');
    }};
    private static final Map<String, PhoneValidationRules> phoneValidationRulesByCountries = new HashMap<>() {{
        put("LT", new PhoneValidationRules(List.of(8), List.of("8", "+370")));
        put("US", new PhoneValidationRules(List.of(10), List.of("+1")));
        put("LV", new PhoneValidationRules(List.of(8), List.of("+371")));
        put("EE", new PhoneValidationRules(List.of(7, 8), List.of("+372")));
    }};

    public static void addPhonePrefixesWithCountry(String countryCode, PhoneValidationRules phoneValidationRules) {
        phoneValidationRulesByCountries.put(countryCode, phoneValidationRules);
    }

    private static <T> void addSinglePhoneValidationRule(String countryCode, T singlePhoneValidationRule) {
        if(phoneValidationRulesByCountries.containsKey(countryCode)) {
            PhoneValidationRules phoneValidationRules = phoneValidationRulesByCountries.get(countryCode);
            addByInstanceType(phoneValidationRules, singlePhoneValidationRule);
            phoneValidationRulesByCountries.replace(countryCode, phoneValidationRules);
        }
    }

    private static <T> void addByInstanceType(PhoneValidationRules phoneValidationRules, T singlePhoneValidationRule) {
        if (singlePhoneValidationRule instanceof Integer) {
            phoneValidationRules.addPhoneLength((Integer) singlePhoneValidationRule);
        }
        else if (singlePhoneValidationRule instanceof String) {
            phoneValidationRules.addPhonePrefix((String) singlePhoneValidationRule);
        }
    };

    public static void addPhonePrefixByCountry(String countryCode, String phonePrefix) {
        addSinglePhoneValidationRule(countryCode, phonePrefix);
    }

    public static void addPhoneLengthByCountry(String countryCode, int phoneLength) {
        addSinglePhoneValidationRule(countryCode, phoneLength);
    }

    public static Map<String, PhoneValidationRules> getPhoneValidationRulesByCountries() {
        return ValidationAcceptableInputs.phoneValidationRulesByCountries;
    }

    public static PhoneValidationRules getPhoneValidationRulesByCountry(String countryCode) {
        return ValidationAcceptableInputs.phoneValidationRulesByCountries.get(countryCode);
    }

    public static void addEmailSuffix(String emailSuffix) {
        ValidationAcceptableInputs.emailSuffixes.add(emailSuffix);
    }

    public static void addInvalidSymbolForEmail(char invalidSymbolForEmail) {
        ValidationAcceptableInputs.invalidSymbolsForEmail.add(invalidSymbolForEmail);
    }

    public static void addSpecialSymbolForPassword(char specialSymbolForPassword) {
        ValidationAcceptableInputs.specialSymbolsForPassword.add(specialSymbolForPassword);
    }

    public static List<String> getEmailSuffixes() {
        return ValidationAcceptableInputs.emailSuffixes;
    }

    public static List<Character> getInvalidSymbolsForEmail() {
        return ValidationAcceptableInputs.invalidSymbolsForEmail;
    }

    public static List<Character> getSpecialSymbolsForPassword() {
        return ValidationAcceptableInputs.specialSymbolsForPassword;
    }
}
