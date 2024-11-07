package edu.westga.cs1302.password_generator.test.password_generator_viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;

class TestPasswordGeneratorViewModel {

    @Test
    public void testInitialValues() {
        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel(1234);

        assertEquals(1, viewModel.minimumLengthProperty().get());
        assertEquals(false, viewModel.mustHaveAtLeastOneDigitProperty().get());
        assertEquals(false, viewModel.mustHaveAtLeastOneLowerCaseLetterProperty().get());
        assertEquals(false, viewModel.mustHaveAtLeastOneUpperCaseLetterProperty().get());
        assertEquals("", viewModel.generatePasswordProperty().get());
    }
    
    @Test
    public void testGeneratePasswordWithOnlyMinimumLengthRequirement() {
        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel(5678);
        viewModel.minimumLengthProperty().set(5);

        viewModel.generatePassword();

        assertEquals(5, viewModel.generatePasswordProperty().get().length());
    }
    
    @Test
    public void testGeneratePasswordWithDigitRequirement() {
        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel(91011);
        viewModel.minimumLengthProperty().set(6);
        viewModel.mustHaveAtLeastOneDigitProperty().set(true);

        viewModel.generatePassword();

        String password = viewModel.generatePasswordProperty().get();
        assertEquals(6, password.length());
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        assertEquals(true, hasDigit);
    }
    
    @Test
    public void testGeneratePasswordWithUpperCaseRequirement() {
        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel(121314);
        viewModel.minimumLengthProperty().set(7);
        viewModel.mustHaveAtLeastOneUpperCaseLetterProperty().set(true);

        viewModel.generatePassword();

        String password = viewModel.generatePasswordProperty().get();
        assertEquals(8, password.length(), "Password length should be 8");
        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        assertTrue(hasUpperCase, "Password should contain at least one uppercase letter");

    }

    @Test
    public void testGeneratePasswordWithLowerCaseRequirement() {
        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel(151617);
        viewModel.minimumLengthProperty().set(8);
        viewModel.mustHaveAtLeastOneLowerCaseLetterProperty().set(true);

        viewModel.generatePassword();

        String password = viewModel.generatePasswordProperty().get();
        assertEquals(11, password.length(), "Password length should be 11");
        boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
        assertTrue(hasLowerCase, "Password should contain at least one lowercase letter");
    }

    @Test
    public void testSimulateDataChangeIncrementsLength() {
        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel(181920);
        int originalLength = viewModel.minimumLengthProperty().get();

        viewModel.simulateDataChange(); 
        assertEquals(originalLength + 1, viewModel.minimumLengthProperty().get());

        viewModel.generatePassword();
        assertEquals(viewModel.minimumLengthProperty().get(), viewModel.generatePasswordProperty().get().length());
    }

}
