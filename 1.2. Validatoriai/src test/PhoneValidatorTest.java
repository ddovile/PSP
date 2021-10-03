import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {

    private PhoneValidator phoneValidator = new PhoneValidator();

    @Test
    void testPhoneNumberForIllegalSymbols_hasIllegalSymbols() {
        assertFalse(phoneValidator.checkPhoneNumberForIllegalSymbols("+37060!87942"));
    }

    @Test
    void testPhoneNumberForIllegalSymbols_noIllegalSymbols() {
        assertTrue(phoneValidator.checkPhoneNumberForIllegalSymbols("+37060487942"));
    }

    @Test
    void testPhoneNumberChangeForNationalPrefix() {
        assertEquals("+37060487942", phoneValidator.changePhoneNumberNationalPrefix("860487942"));
    }

    @Test
    void testAddingNewValidationRule_CorrectNumber() {
        phoneValidator.addNewValidationRule("+371", 12, "LV");
        assertTrue(phoneValidator.checkPhoneNumber("+37167284828", "LV"));
    }

    @Test
    void testAddingNewValidationRule_TooLongInternationalNumber() {
        assertFalse(phoneValidator.checkPhoneNumber("+44640247822456", "UK"));
    }

    @Test
    void checkLtNumber_IncorrectPrefix() {
        assertFalse(phoneValidator.checkPhoneNumber("+37164033084"));
    }

    @Test
    void checkLtNumber_CorrectNumber() {
        assertTrue(phoneValidator.checkPhoneNumber("+37064033084"));
    }
}
