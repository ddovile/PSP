package edu.ernestas;

import edu.ernestas.ConcreteValidators.PhoneValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    private static final PhoneValidator phoneValidator = new PhoneValidator();

    @Test
    public void phoneCorrect() {
        String phone = "+37061234567";
        assertTrue(phoneValidator.isValid(phone));
    }

    @Test
    void phoneHasIllegalSymbols() {
        String phone = "+37061234A67";
        assertFalse(phoneValidator.isValid(phone));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+370612367", "+370612345678"})
    void phoneTooLongOrTooShort(String phone) {
        assertFalse(phoneValidator.isValidByCountryCode(phone, "LT"));
    }

    @Test
    public void phoneConverts() {
        String phone = "861234568";
        assertEquals("+37061234568", phoneValidator.convertPhoneNumber(phone));
    }

}