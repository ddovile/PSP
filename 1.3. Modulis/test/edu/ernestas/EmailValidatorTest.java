package edu.ernestas;

import edu.ernestas.ConcreteValidators.EmailValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private static final EmailValidator emailValidator = new EmailValidator();

    @Test
    public void emailCorrect() {
        String email = "test@mif.lt";
        assertTrue(emailValidator.isValid(email));
    }

    @Test
    void emailWithoutAtSymbol() {
        String email = "testmif.lt";
        assertFalse(emailValidator.isValid(email));
    }

    @Test
    void emailWithMultipleAtSymbols() {
        String email = "te@st@mif.lt";
        assertFalse(emailValidator.isValid(email));
    }

    @Test
    void emailWithIllegalSymbols() {
        String email = "te#st@mif.lt";
        assertFalse(emailValidator.isValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@mi_f.lt", "test@mif.l%t", "test@mi=f.lt"})
    void emailHasBadDomainOrTLD(String email) {
        assertFalse(emailValidator.isValid(email));
    }

}