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
	 * @return true 
	 */
	public boolean addTask(Task task) {
	    if (task == null) {
	        throw new IllegalArgumentException("Task cannot be null");
	    }
	    if (this.taskLookup.containsKey(task.getTitle())
	    		&& this.taskLookup.get(task.getTitle()).getDescription().equals(task.getDescription())) {
	        return false;
	    }

	    this.tasks.add(task);
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
	    boolean removedFromList = this.tasks.remove(task);
	    this.taskLookup.remove(task.getTitle());

	    return removedFromList;
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
