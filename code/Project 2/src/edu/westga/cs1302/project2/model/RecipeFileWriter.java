package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Writes the recipe to a file
 * 
 * @author Yeni Almanza
 * @version Fall 2024
 */
public class RecipeFileWriter {
	private File file;
	
	/**
	 * Constructs a RecipeFileWriter object with the specified file path
	 * 
	 * @param filePath the path to the file
	 */
	public RecipeFileWriter(String filePath) {
		this.file = new File(filePath);
	}
	
	/**
	 * Writes the recipe to the file
	 * 
	 * @param recipe the recipe to be written
	 * @throws IOException if error occurs while writing to file
	 * @throws IllegalStateException if file cannot be written to
	 */
	public void writeRecipe(Recipe recipe) throws IOException, IllegalStateException {
		if (!this.file.exists()) {
			this.file.createNewFile();
		}
		
		try (Scanner reader = new Scanner(this.file)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				if (line.equals(recipe.getName())) {
					throw new IllegalStateException("Recipe with this name already exists.");
				}
			}
		} catch (IOException fileError) {
			throw new IOException("Erro reading from file: " + fileError.getMessage(), fileError);
		}
		
		try (FileWriter writer = new FileWriter(this.file, true)) {
			writer.write(RecipeUtility.converRecipeToString(recipe) + System.lineSeparator());
		}  catch (IOException fileError) {
			throw new IOException("Erro reading from file: " + fileError.getMessage(), fileError);
		}
	}
}
