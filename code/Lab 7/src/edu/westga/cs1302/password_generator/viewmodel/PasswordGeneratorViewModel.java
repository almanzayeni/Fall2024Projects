package edu.westga.cs1302.password_generator.viewmodel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * View model for the passoword generator
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class PasswordGeneratorViewModel {
	
	private PasswordGenerator generator;
	
	private IntegerProperty minimumLengthProperty;
	private BooleanProperty mustHaveAtLeastOneDigitProperty;
	private BooleanProperty mustHaveAtLeastOneLowerCaseLetterProperty;
	private BooleanProperty mustHaveAtLeastOneUpperCaseLetterProperty;
	private StringProperty generatePasswordProperty;
	
	/**
	 * Instantiates a new viewmodel with speecified random seed.
	 * 
	 * @param seed the seed for the random number generator
	 */
	public PasswordGeneratorViewModel(long seed) {
		this.generator = new PasswordGenerator(seed);
		this.minimumLengthProperty = new SimpleIntegerProperty(1);
		this.mustHaveAtLeastOneDigitProperty = new SimpleBooleanProperty(false);
		this.mustHaveAtLeastOneLowerCaseLetterProperty = new SimpleBooleanProperty(false);
		this.mustHaveAtLeastOneUpperCaseLetterProperty = new SimpleBooleanProperty(false);
		this.generatePasswordProperty = new SimpleStringProperty("");
	}
	
	/**
	 * Gets the property for the minimum length of the password.
	 * 
	 * @return the minimum length property
	 */
	public IntegerProperty minimumLengthProperty() {
		return this.minimumLengthProperty;
	}
	
	/**
	 * Gets the property that indicated whether the password must contain
	 * at least one digit.
	 * 
	 * @return the property of the digit requirement
	 */
	public BooleanProperty mustHaveAtLeastOneDigitProperty() {
		return this.mustHaveAtLeastOneDigitProperty;
	}
	
	/**
	 * Gets the property that indicated whether the password must contain
	 * at least one lowercase letter.
	 * 
	 * @return the property of the lowercase requirement
	 */
	public BooleanProperty mustHaveAtLeastOneLowerCaseLetterProperty() {
		return this.mustHaveAtLeastOneLowerCaseLetterProperty;
	}
	
	/**
	 * Gets the property that indicated whether the password must contain
	 * at least one uppercase letter.
	 * 
	 * @return the property of the uppercase requirement
	 */
	public BooleanProperty mustHaveAtLeastOneUpperCaseLetterProperty() {
		return this.mustHaveAtLeastOneUpperCaseLetterProperty;
	}
	
	/**
	 * Gets the property that holds the generated password
	 * 
	 * @return the property for the generated password
	 */
	public StringProperty generatePasswordProperty() {
		return this.generatePasswordProperty;
	}
	
    /**
     * Updates the PasswordGenerator with the current settings and generates a new password.
     */
    public void generatePassword() {
        this.generator.setMinimumLength(this.minimumLengthProperty.get());
        this.generator.setMustHaveAtLeastOneDigit(this.mustHaveAtLeastOneDigitProperty.get());
        this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustHaveAtLeastOneLowerCaseLetterProperty.get());
        this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustHaveAtLeastOneUpperCaseLetterProperty.get());

        String password = this.generator.generatePassword();
        this.generatePasswordProperty.set(password);
    }

    /**
     * Simulates a change to the minimum length requirement and regenerates the password.
     * Used to demonstrate how the property bindings update the UI.
     */
    public void simulateDataChange() {
        this.minimumLengthProperty.set(this.minimumLengthProperty.get() + 1);
        this.generatePassword();
    }
}
