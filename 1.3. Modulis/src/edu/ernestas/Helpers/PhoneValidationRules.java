package edu.ernestas.Helpers;

import java.util.List;

public class PhoneValidationRules {
    private List<Integer> phoneLengths;
    private List<String> phonePrefixes;

    public PhoneValidationRules(List<Integer> phoneLengths, List<String> phonePrefixes) {
        this.phoneLengths = phoneLengths;
        this.phonePrefixes = phonePrefixes;
    }

    public void addPhonePrefix(String phonePrefix) {
            this.phonePrefixes.add(phonePrefix);
    }

    public List<String> getPhonePrefixes() {
        return this.phonePrefixes;
    }

    public void setPhonePrefixes(List<String> phonePrefixes) {
        this.phonePrefixes = phonePrefixes;
    }

    public void addPhoneLength(int phoneLength) {
        this.phoneLengths.add(phoneLength);
    }

    public List<Integer> getPhoneLengths() {
        return phoneLengths;
    }

    public void setPhoneLengths(List<Integer> phoneLengths) {
        this.phoneLengths = phoneLengths;
    }
}
