package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {
    @FXML private Button addTaskButton;

    @FXML private Button cancelButton;

    @FXML private TextArea description;

    @FXML private TextField title;
    
    private TaskViewModel vm;
    
 /**
     * Initializes the window
     * 
     * @param viewModel the task view model
     */
	public void bindToVM(TaskViewModel viewModel) {
		this.vm = viewModel;
		this.title.textProperty().bindBidirectional(viewModel.getTaskTitle());
        this.description.textProperty().bindBidirectional(viewModel.getTaskDescription());
		
	}
	
    @FXML
    private void initialize() {
        this.cancelButton.setOnAction(event -> {
            Stage stage = (Stage) this.cancelButton.getScene().getWindow();
            stage.close();
        });
        
        this.addTaskButton.setOnAction(event -> {
            String taskTitle = this.title.getText();
            String taskDescription = this.description.getText();
            this.vm.addTask(taskTitle, taskDescription);

            Stage stage = (Stage) this.addTaskButton.getScene().getWindow();
            stage.close();
        });
    }
}
