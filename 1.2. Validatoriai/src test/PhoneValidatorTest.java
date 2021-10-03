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
    void testAddingNewValidationRule_withInvalidPrefix() {
        assertFalse(phoneValidator.addNewValidationRule("+49", 11, "LT"));
    }

    @Test
    void testAddingNewValidationRule_withInvalidLength() {
        assertFalse(phoneValidator.addNewValidationRule("+370", 5, "LT"));
    }

    @Test
    void testAddingNewValidationRule_withCorrectCase() {
        assertTrue(phoneValidator.addNewValidationRule("+370", 11, "LT"));
    }
}
