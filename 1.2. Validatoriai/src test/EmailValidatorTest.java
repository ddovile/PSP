import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {

    private EmailValidator emailValidator = new EmailValidator();

    @Test
    void testForAtSymbol_hasValidAtSymbol() {
        assertTrue(emailValidator.checkForAtSymbol("tom@gmail.com"));
    }

    @Test
    void testForAtSymbol_noValidAtSymbol() {
        assertFalse(emailValidator.checkForAtSymbol("tomgmail.com"));
    }

    @Test
    void testForIllegalSymbols_hasIllegalSymbols() {
        assertFalse(emailValidator.checkForIllegalSymbols("tom!@gmail.com"));
    }

    @Test
    void testForIllegalSymbols_noIllegalSymbols() {
        assertTrue(emailValidator.checkForIllegalSymbols("tom@gmail.com"));
    }

    @Test
    void testForDomainAndTLD_validDomainAndTLD() {
        assertTrue(emailValidator.checkDomainAndTLD("tom@gmail.com"));
    }

    @Test
    void testForDomainAndTLD_invalidDomainAndTLD() {
        assertFalse(emailValidator.checkDomainAndTLD("tom@a.a"));
    }
}
