import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    private PasswordChecker passwordChecker = new PasswordChecker();

    @Test
    void testPasswordLength_validLength() {
        assertTrue(passwordChecker.checkPasswordLength("password", 8));
    }

    @Test
    void testPasswordLength_invalidLength() {
        assertFalse(passwordChecker.checkPasswordLength("pass", 8));
    }

    @Test
    void testPasswordForUppercase_hasUppercase() {
        assertTrue(passwordChecker.checkPasswordForUppercase("pAssword123"));
    }

    @Test
    void testPasswordForUppercase_noUppercase() {
        assertFalse(passwordChecker.checkPasswordForUppercase("password123"));
    }

    @Test
    void testPasswordForSpecialSymbols_noSpecialSymbols() {
        assertFalse(passwordChecker.checkPasswordForSpecialSymbols("password"));
    }

    @Test
    void testPasswordForSpecialSymbols_hasSpecialSymbols() {
        assertTrue(passwordChecker.checkPasswordForSpecialSymbols("password!"));
    }
}
