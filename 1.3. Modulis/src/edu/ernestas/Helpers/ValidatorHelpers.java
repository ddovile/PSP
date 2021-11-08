package edu.ernestas.Helpers;

import java.util.List;

public final class ValidatorHelpers {

    public static boolean containsStrings(String sourceString, List<String> substrings) {
        for (String substring : substrings) {
            if(containsString(sourceString, substring)) {
                return true;
            }
        }
        return false;
    }

    public static ValidationResult checkIfStringIsNotNull(String string, String errorMessage) {
        if (string == null) {
            return new ValidationResult(errorMessage, false);
        }
        return new ValidationResult();
    }

    public static int countSubstringOccurrences(String sourceString, String substring) {
        int counter = 0;
        int sourceStringLength = sourceString.length();
        int substringLength = substring.length();
        if (sourceStringLength < substringLength) {
            return 0;
        }
        for (int i = 0; i <= sourceStringLength - substringLength; i++) {
            String sourceSubstring = sourceString.substring(i, substringLength+i);
            if (sourceSubstring.equals(substring)) {
                counter++;
            }
        }
        return counter;
    }

    public static String getSubstringAfterPattern(String sourceString, String substring) {
        int sourceStringLength = sourceString.length();
        int substringLength = substring.length();
        if (sourceStringLength < substringLength) {
            return "";
        }
        for (int i = 0; i <= sourceStringLength - substringLength; i++) {
            String sourceSubstring = sourceString.substring(i, substringLength+i);
            if (sourceSubstring.equals(substring)) {
                return sourceString.substring(i+substringLength, sourceStringLength);
            }
        }
        return "";
    }

    public static String getSubstringBeforePattern(String sourceString, String substring) {
        int sourceStringLength = sourceString.length();
        int substringLength = substring.length();
        if (sourceStringLength < substringLength) {
            return "";
        }
        for (int i = 0; i <= sourceStringLength - substringLength; i++) {
            String sourceSubstring = sourceString.substring(i, substringLength+i);
            if (sourceSubstring.equals(substring)) {
                return sourceString.substring(0, i);
            }
        }
        return "";
    }

    public static boolean containsString(String sourceString, String substring) {
        int sourceStringLength = sourceString.length();
        int substringLength = substring.length();
        if (sourceStringLength < substringLength) {
            return false;
        }
        for (int i = 0; i <= sourceStringLength - substringLength; i++) {
            String sourceSubstring = sourceString.substring(i, substringLength+i);
            if (sourceSubstring.equals(substring)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAtLeastOneUpperCase(String string) {
        for (char character : string.toCharArray()) {
            if (Character.isLetter(character) && Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAtLeastOneLowerCase(String string) {
        for (char character : string.toCharArray()) {
            if (Character.isLetter(character) && Character.isLowerCase(character)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLowerCase(String string) {
        for (char character : string.toCharArray()) {
            if (!Character.isLetter(character) || !Character.isLowerCase(character)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUpperCase(String string) {
        for (char character : string.toCharArray()) {
            if (!Character.isLetter(character) || !Character.isUpperCase(character)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDigit(String string) {
        for (char character : string.toCharArray()) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLetter(String string) {
        for (char character : string.toCharArray()) {
            if (!Character.isLetter(character)) {
                return false;
            }
        }
        return true;
    }

    public static String getSubstring(String string, int charactersLength) {
        int counter = 0;
        String newString = "";
        for (char character : string.toCharArray()) {
            if(counter == charactersLength) {
                return newString;
            }
            counter++;
            newString += character;
        }
        return newString;
    }

    public static String getSubstring(String string, int charactersLength, int startIndex) {
        int counter = 0;
        String newString = "";
        if (startIndex < 0 || startIndex >= string.length()) {
            startIndex = 0;
        }
        for (int i = startIndex; i < string.length(); i++) {
            char character = string.charAt(i);
            if(counter == charactersLength) {
                return newString;
            }
            counter++;
            newString += character;
        }
        return newString;
    }
}
