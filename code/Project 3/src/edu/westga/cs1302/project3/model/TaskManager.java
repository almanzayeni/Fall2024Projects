package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks
 * 
 * @author Yeni A
 * @version Fall 2024
 */
public class TaskManager {
	private List<Task> tasks;
	
	/**
	 * Initializes a list of tasks
	 * 
	 * @precondition none
	 * @postconditiong getTasks().equals(tasks)
	 */
	public TaskManager() {
		this.tasks = new ArrayList<>();
	}

	/**
	 * Add a task 
	 * 
	 * @return the task added
	 */
	public List<Task> getTasks() {
		return this.tasks;
	}
	
	/**
	 * Add a task 
	 * 
	 * @param task the task to add
	 */
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		}
		this.tasks.add(task);
	}
	
	/**
	 * Removes a task
	 * 
	 * @param task the task to remove
	 * @return the list with removed task
	 */
	public boolean removeTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		}
		return this.tasks.remove(task);
	}
	
    /**
	 * Display task
	 * 
	 * @precodnition none
	 * @postcodnition none
	 */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tasks List:\n");
        for (Task task : this.tasks) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }
	
}
