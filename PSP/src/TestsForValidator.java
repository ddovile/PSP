import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
public class TestsForValidator 
{
    Validator validator;

    @BeforeEach
    public void setup()
    {
        validator = new Validator();
    }

    //Tests for password
    @Test
    void password_tooShort_false ()
    {
        String password = "Pas123*";
        int minLength = 10;
        boolean result = validator.validatePassword(password, minLength);
        assertFalse(result);
    }

    @Test
    void password_noSpecialSymbol_false ()
    {    
        String password = "Password123@";
        char[] specialSymbols = {'*', '/', '-'};
        boolean result = validator.validatePassword(password, specialSymbols);
        assertFalse(result);    
    }

    @Test
    void password_noUppercase_false ()
    {   
        String password = "password123*";
        boolean result = validator.validatePassword(password);
        assertFalse(result);     
    }

    @Test
    void password_satisfiesAllConditions_true ()
    {    
        String password = "Password123*";
        boolean result = validator.validatePassword(password);
        assertTrue(result);   
    }

    //Tests for email
    @Test
    void email_noAtSymbol_false ()
    {
        String email = "email.gmail.com";
        boolean result = validator.validateEmail(email);
        assertFalse(result);
    }

    @Test
    void email_hasNotValidSymbol_false ()
    {
        String email = "email;@gmail.com";
        boolean result = validator.validateEmail(email);
        assertFalse(result);
    }

    @Test
    void email_hasBadDomain_false ()
    {
        String email = "email@gmailas.lit";
        boolean result = validator.validateEmail(email);
        assertFalse(result);
    }

    @Test
    void email_satisfiesAllConditions_true ()
    {
        String email = "email@gmail.com";
        boolean result = validator.validateEmail(email);
        assertFalse(result);
    }

    //Tests for phone number
    @Test
    void number_containsSpecialSymbol_false ()
    {
        String phoneNumber = "+370640330*4";
        boolean result = validator.validateNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void number_containsLetter_false ()
    {
        String phoneNumber = "+370640330A4";
        boolean result = validator.validateNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void number_tooLongLtNumber_false ()
    {
        String phoneNumber = "+3706403330844";
        boolean result = validator.validateNumber(phoneNumber);
        assertFalse(result);
    }

    @Test
    void number_tooLongForeignCountryNumber_false ()
    {
        String phoneNumber = "+3159869045343"; 
        String country = "UK";
        boolean result =  validator.validateNumber(phoneNumber, country);
        assertFalse(result);
    }

    @Test
    void number_wrongPrefixForeignCountryNumber_false ()
    {
        String phoneNumber = "+41598690453"; 
        String country = "UK";
        boolean result =  validator.validateNumber(phoneNumber, country);
        assertFalse(result);
    }

    @Test
    void number_validLtNumber_true ()
    {
        String phoneNumber = "864033084";
        boolean result = validator.validateNumber(phoneNumber);
        assertTrue(result);
    }

    @Test
    void number_validForeignCountryNumber_true ()
    {
        String phoneNumber = "+31598690453";
        String country = "UK";
        boolean result =  validator.validateNumber(phoneNumber, country);
        assertTrue(result);
    }
}


