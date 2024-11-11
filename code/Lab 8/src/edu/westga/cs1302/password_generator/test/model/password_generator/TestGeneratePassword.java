package edu.westga.cs1302.password_generator.test.model.password_generator;

import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;

class TestGeneratePassword {

    @Test
    void testMinimumLength1NoOtherRestriction() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(1);
        generator.setMustHaveAtLeastOneUpperCaseLetter(false);
        generator.setMustHaveAtLeastOneLowerCaseLetter(false);
        generator.setMustHaveAtLeastOneDigit(false);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 1, "Checking length of generated password");
    }

	@Test
	void testMinimumLength3NoOtherRestriction() {
		PasswordGenerator generator = new PasswordGenerator(1);
		generator.setMinimumLength(3);
		generator.setMustHaveAtLeastOneUpperCaseLetter(false);
		generator.setMustHaveAtLeastOneLowerCaseLetter(false);
		generator.setMustHaveAtLeastOneDigit(false);
		
		String result = generator.generatePassword();

		assertTrue(3 <= result.length(), "checking length of generated password");
	}

    @Test
    void testMinimumLength3AtLeastOneUpperCaseLetterNoOtherRestriction() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(3);
        generator.setMustHaveAtLeastOneUpperCaseLetter(true);
        generator.setMustHaveAtLeastOneLowerCaseLetter(false);
        generator.setMustHaveAtLeastOneDigit(false);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 3, "Checking length of generated password");
        assertTrue(result.chars().anyMatch(Character::isUpperCase), "Checking for at least one uppercase letter");
    }

    @Test
    void testMinimumLength3AtLeastOneDigitNoOtherRestriction() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(3);
        generator.setMustHaveAtLeastOneUpperCaseLetter(false);
        generator.setMustHaveAtLeastOneLowerCaseLetter(false);
        generator.setMustHaveAtLeastOneDigit(true);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 3, "Checking length of generated password");
        assertTrue(result.chars().anyMatch(Character::isDigit), "Checking for at least one digit");
    }

    @Test
    void testMinimumLength3AtLeastOneLowerCaseLetterNoOtherRestriction() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(3);
        generator.setMustHaveAtLeastOneUpperCaseLetter(false);
        generator.setMustHaveAtLeastOneLowerCaseLetter(true);
        generator.setMustHaveAtLeastOneDigit(false);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 3, "Checking length of generated password");
        assertTrue(result.chars().anyMatch(Character::isLowerCase), "Checking for at least one lowercase letter");
    }

    @Test
    void testMinimumLength3AtLeastOneLowerCaseLetterAtLeastOneUpperCaseLetterNoOtherRestriction() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(3);
        generator.setMustHaveAtLeastOneUpperCaseLetter(true);
        generator.setMustHaveAtLeastOneLowerCaseLetter(true);
        generator.setMustHaveAtLeastOneDigit(false);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 3, "Checking length of generated password");
        assertTrue(result.chars().anyMatch(Character::isUpperCase), "Checking for at least one uppercase letter");
        assertTrue(result.chars().anyMatch(Character::isLowerCase), "Checking for at least one lowercase letter");
    }

    @Test
    void testMinimumLength3AtLeastOneLowerCaseLetterAtLeastOneDigitNoOtherRestriction() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(3);
        generator.setMustHaveAtLeastOneUpperCaseLetter(false);
        generator.setMustHaveAtLeastOneLowerCaseLetter(true);
        generator.setMustHaveAtLeastOneDigit(true);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 3, "Checking length of generated password");
        assertTrue(result.chars().anyMatch(Character::isLowerCase), "Checking for at least one lowercase letter");
        assertTrue(result.chars().anyMatch(Character::isDigit), "Checking for at least one digit");
    }

    @Test
    void testMinimumLength3AtLeastOneUpperCaseLetterAtLeastOneDigitNoOtherRestriction() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(3);
        generator.setMustHaveAtLeastOneUpperCaseLetter(true);
        generator.setMustHaveAtLeastOneLowerCaseLetter(false);
        generator.setMustHaveAtLeastOneDigit(true);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 3, "Checking length of generated password");
        assertTrue(result.chars().anyMatch(Character::isUpperCase), "Checking for at least one uppercase letter");
        assertTrue(result.chars().anyMatch(Character::isDigit), "Checking for at least one digit");
    }

    @Test
    void testMinimumLength3AtLeastOneUpperCaseLetterAtLeastOneLowerCaseLetterAtLeastOneDigit() {
        PasswordGenerator generator = new PasswordGenerator(1);
        generator.setMinimumLength(3);
        generator.setMustHaveAtLeastOneUpperCaseLetter(true);
        generator.setMustHaveAtLeastOneLowerCaseLetter(true);
        generator.setMustHaveAtLeastOneDigit(true);

        String result = generator.generatePassword();
        assertTrue(result.length() >= 3, "Checking length of generated password");
        assertTrue(result.chars().anyMatch(Character::isUpperCase), "Checking for at least one uppercase letter");
        assertTrue(result.chars().anyMatch(Character::isLowerCase), "Checking for at least one lowercase letter");
        assertTrue(result.chars().anyMatch(Character::isDigit), "Checking for at least one digit");
    } 

}
