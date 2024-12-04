package edu.westga.cs1302.project3.model;

/**
 * Stores a task 
 * 
 * @author Yeni
 * @version Fall 2024
 */
public class Task {
	private String title;
	private String description;
	
	/**
	 * Initializes a new task with a title and a description
	 * 
	 * @precondition title != null && !title.isEmpty() &&
	 *               description != null && !description.isEmpty()
	 * @postcondition getTitle().equals(title) &&
	 *                getDescription().equals(description)
	 * @param title the title of the task
	 * @param description the description of the task
	 */
	public Task(String title, String description) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}
		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or empty");
		}
		this.title = title;
		this.description = description;
	}

	/**
	 * Gets the title of the task 
	 * 
	 * @precondition none
	 * @postcodnition none
	 * 
	 * @return the title, title of the task
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the description of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description, description of the task
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Display title and description
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@Override
	public String toString() {
		return this.title + " " + this.description;
	}
	
}
