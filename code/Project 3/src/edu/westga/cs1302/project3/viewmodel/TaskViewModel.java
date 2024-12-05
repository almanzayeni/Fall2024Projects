package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileManager;
import edu.westga.cs1302.project3.model.TaskManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * View model for properties
 * @author Yeni A
 * @version Fall 2024
 */
public class TaskViewModel {
	private TaskManager taskManager;
	private ListProperty<Task> tasks;
	private ObjectProperty<Task> selectedTask;
	private StringProperty taskTitle;
	private StringProperty taskDescription;
	
	/**
	 * Initializes the properties
	 */
	public TaskViewModel() {
        this.taskManager = new TaskManager();
        this.tasks = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<Task>()));
        this.selectedTask = new SimpleObjectProperty<>();
        this.taskTitle = new SimpleStringProperty("");
        this.taskDescription = new SimpleStringProperty("");

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
     * @precodnition none
     * @postcondition none
     * 
     * @return the list of tasks
     */
    public ListProperty<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the selected task.
     * 
     * @precodnition none
     * @postcondition none
     * 
     * @return the selected task
     */
    public ObjectProperty<Task> getSelectedTask() {
        return this.selectedTask;
    }
    
    /**
     * Gets the title of the task
     * 
     * @precodnition none
     * @postcondition none
     * 
     * @return the title of the task
     */
    public StringProperty getTaskTitle() {
        return this.taskTitle;
    }
    
    /**
     * Gets the description of the task
     * 
     * @precodnition none
     * @postcondition none
     * 
     * @return the description of the task
     */
    public StringProperty getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Adds a task to the task manager.
     * 
     * @param title       the title of the task
     * @param description the description of the task
     * @return is Added when task is added
     */
    public boolean addTask(String title, String description) {
        Task newTask = new Task(title, description);
        boolean isAdded = this.taskManager.addTask(newTask);
        if (isAdded) {
            // Refresh the task list
            this.tasks.set(FXCollections.observableArrayList(this.taskManager.getTasks()));
        }
        return isAdded;
    }
    
    /**
     * Removes the currently selected task from the task manager.
     */
    public void removeSelectedTask() {
        Task selected = this.selectedTask.get();
        if (selected != null) {
            this.taskManager.removeTask(selected); 
            this.tasks.set(FXCollections.observableArrayList(this.taskManager.getTasks())); 
        }
    }
    
    /**
     * Loads tasks from a file selected by the user.
     *
     * @param stage the stage used to show the file chooser
     * @throws IOException if an I/O error occurs during file reading
     */
    public void loadTasksFromFile(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                TaskManager loadedTasks = TaskFileManager.loadTasks(selectedFile.getAbsolutePath());
                if (loadedTasks.getTasks().isEmpty()) {
                    throw new IllegalArgumentException("The selected file does not contain valid tasks.");
                }
                this.taskManager = loadedTasks;
                this.tasks.set(FXCollections.observableArrayList(this.taskManager.getTasks()));
            } catch (IllegalArgumentException | IOException err) {
                throw err;
            }
        }
    }
    
    /**
     * Saves the current tasks to a file selected by the user.
     *
     * @param stage the stage used to show the file chooser
     * @throws IOException if an I/O error occurs during file writing
     */
    public void saveTasksToFile(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile != null) {
            try {
                TaskFileManager.saveTasks(this.taskManager, selectedFile.getAbsolutePath());
            } catch (IOException err) {
                throw new IOException("Unable to write to the file. Ensure the file is not locked or read-only.", err);
            }
        }
    }
}
