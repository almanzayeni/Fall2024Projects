package edu.westga.cs1302.project3.model;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages the files for the tasks(s)
 * 
 * @author Yeni A
 * @version Fall 2024
 */
public class TaskFileManager {
	
	/**
	 * Saves the tasks from the given TaskManger to a file
	 * 
	 * @param taskManager the task manager containing the task to be saved
	 * @param filePath the path of the file to save the tasks to
	 * @throws IOException if and I/O error occurs
	 */
	public static void saveTasks(TaskManager taskManager, String filePath) throws IOException {
		try (FileWriter writer = new FileWriter(filePath)) {
			for (Task task : taskManager.getTasks()) {
				writer.write(task.getTitle() + ": " + task.getDescription() + System.lineSeparator());
			}
		}
		
	}
	
	/**
	 * Loads tasks form a specific file into a new TaskManager
	 * 
	 * @param filePath the path if the file to load task from
	 * @return a new TaskManger containing the loaded tasks
	 * @throws IOException if I/O error occurs
	 */
	public static TaskManager loadTasks(String filePath) throws IOException {
	    TaskManager taskManager = new TaskManager();
	    try (Scanner scanner = new Scanner(new File(filePath))) {
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split(":", 2);
	            if (parts.length == 2) {
	                taskManager.addTask(new Task(parts[0], parts[1].trim()));
	            }
	        }
	    }
	    return taskManager;
	}
}
