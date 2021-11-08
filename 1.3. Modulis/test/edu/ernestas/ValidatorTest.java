package edu.ernestas;

import edu.ernestas.Validators.Validator;
import edu.ernestas.Validators.ValidatorBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        //Arrange
        validator = new ValidatorBuilder().build();
    }

    @ParameterizedTest
    @CsvSource({ "ernestas@gmail.com, true", "bad.com, false", "bad_emailetamail.com, false" })
    void testIfEmailHasEtaSymbolString(String inputToValidate, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isEmailValid(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "ernestas21313_lol@gmail.com, true", "e-lol@gmail.com, true", "e-[][lol@gmail.com, false", "//'''[@gmail.com, false" })
    void testIfEmailDoesNotContainInvalidSymbols(String inputToValidate, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isEmailValid(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "ernestas@mail.com, true", "ernestas@123.com, true", "ernestas21313_lol@gmail.c, false", "ernestas@.com, false", "ernestas@gmail., false" })
    void testIfEmailHasCorrectDomainAndTopLevelDomain(String inputToValidate, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isEmailValid(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "alejosdJosj#d, true", "Verylonglonglo.ngpassword, true", "short, false", "v, false" })
    void testIfPasswordIsNotShorterThanX(String inputToValidate, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isPasswordValid(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "ChickenWings., true", "?VERYUPPERCASE, true", "verylowercase, false", "Pa#scalCase, true", "(hmmmmMmmmm, true" })
    void testIfPasswordHasUppercaseSymbols(String inputToValidate, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isPasswordValid(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "Password!., true", "fail, false", "badpAssword, false", "&%$#@!*_-/\\()^[]{}<>?~+-.:;'\"`, false" })
    void testIfPasswordHasSpecialSymbol(String inputToValidate, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isPasswordValid(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "865321597, true", "865321a97, false", "very bad, false" })
    void testIfPhoneOnlyHasNumbers(String inputToValidate, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isPhoneValid(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "865321597, +37065321597", "765321597, 765321597" })
    void testIfPhoneIsUpdatedWithPlus370InsteadOfStartingNumberWhenStartingNumberIs8(String inputToValidate, String expectedResult) {
        //Act
        String actualResult = validator.transformPhoneShortPrefixToFullPrefix(inputToValidate);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({ "861101111, LT, true", "861101111, CO, false", "+16465554099, US, true" })
    void testIfPhoneHasValidLengthOrPrefixByCountry(String inputToValidate, String countryCode, boolean expectedResult) {
        //Act
        boolean actualResult = validator.isPhoneValidByCountryCode(inputToValidate, countryCode);

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}
