package edu.ernestas;

import edu.ernestas.ConcreteValidators.PasswordChecker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    private static final PasswordChecker passwordChecker = new PasswordChecker();

    @Test
    public void passwordCorrect () {
        String password = "testPSP123.";
        assertTrue(passwordChecker.isValid(password));
    }

    @Test
    void passwordTooShort() {
        String password = "tP3.";
        assertFalse(passwordChecker.isValid(password));
    }

    @Test
    void passwordWithoutUppercaseSymbols() {
        String password = "testpsp123.";
        assertFalse(passwordChecker.isValid(password));
    }

    @Test
    void passwordWithoutSpecialSymbols() {
        String password = "testPSP123";
        assertFalse(passwordChecker.isValid(password));
    }
}