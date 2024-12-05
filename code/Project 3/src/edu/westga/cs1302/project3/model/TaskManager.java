package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of tasks
 * 
 * @author Yeni A
 * @version Fall 2024
 */
public class TaskManager {
	private List<Task> tasks;
	 private Map<String, Task> taskLookup;
	
	/**
	 * Initializes a list of tasks
	 * 
	 * @precondition none
	 * @postconditiong getTasks().equals(tasks)
	 */
	public TaskManager() {
		this.tasks = new ArrayList<>();
		this.taskLookup = new HashMap<>();
	}

	/**
	 * Add a task 
	 * 
	 * @return the task added
	 */
	public List<Task> getTasks() {
		 return List.copyOf(this.taskLookup.values());
	}
	
	/**
	 * Add a task 
	 * 
	 * @param task the task to add
	 * @return true, when 
	 */
	public boolean addTask(Task task) {
        if (this.taskLookup.containsKey(task.getTitle())) {
            Task existingTask = this.taskLookup.get(task.getTitle());
            if (existingTask.getDescription().equals(task.getDescription())) {
                return false;
            }
        }
        this.taskLookup.put(task.getTitle(), task);
        return true;
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
