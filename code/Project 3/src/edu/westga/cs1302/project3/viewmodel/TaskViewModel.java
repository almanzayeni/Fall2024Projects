package edu.westga.cs1302.project3.viewmodel;

import java.util.ArrayList;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

/**
 * View model for properties
 * @author Yeni A
 * @version Fall 2024
 */
public class TaskViewModel {
	private TaskManager taskManager;
	private ListProperty<Task> tasks;
	private ObjectProperty<Task> selectedTask;
	
	/**
	 * Initializes the properties
	 */
	public TaskViewModel() {
        this.taskManager = new TaskManager();
        this.tasks = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<Task>()));
        this.selectedTask = new SimpleObjectProperty<>();

        this.addDefaultTasks();
        this.tasks.set(FXCollections.observableArrayList(this.taskManager.getTasks()));
    }

    /**
     * Adds some default tasks to the TaskManager.
     */
    private void addDefaultTasks() {
        this.taskManager.addTask(new Task("Buy groceries", "Milk, eggs, bread"));
        this.taskManager.addTask(new Task("Finish project", "Complete coding and documentation"));
        this.taskManager.addTask(new Task("Clean room", "Vacuum and organize"));
    }

    /**
     * Gets the list of tasks.
     * 
     * @return the list of tasks
     */
    public ListProperty<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the selected task.
     * 
     * @return the selected task
     */
    public ObjectProperty<Task> getSelectedTask() {
        return this.selectedTask;
    }
    
    /**
     * Adds a new task to the task list.
     * 
     * @param title the title of the new task
     * @param description the description of the new task
     */
    public void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        this.taskManager.addTask(newTask);
        this.tasks.set(FXCollections.observableArrayList(this.taskManager.getTasks()));
    }
    
    /**
     * Removes the selected task.
     */
    public void removeTask() {
        Task taskToRemove = this.selectedTask.get();
        if (taskToRemove != null) {
            this.taskManager.removeTask(taskToRemove);
            this.tasks.set(FXCollections.observableArrayList(this.taskManager.getTasks()));
        }
    }
}
