package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
            String taskTitle = this.title.getText().trim();
            String taskDescription = this.description.getText().trim();
            if (taskTitle.isEmpty() || taskDescription.isEmpty()) {
                this.showAlert("Error", "Both title and description must be provided.", AlertType.ERROR);
                return;
            }

            boolean isTaskAdded = this.vm.addTask(taskTitle, taskDescription);
            if (isTaskAdded) {
                Stage stage = (Stage) this.addTaskButton.getScene().getWindow();
                stage.close();
            } else {
                this.showAlert("Error", "A task with the same title and description already exists.", AlertType.ERROR);
            }
        });
    }
    
    /**
     * Helper method to show an alert with a custom message and alert type.
     * 
     * @param title the title of the alert
     * @param message the message to display
     * @param alertType the type of alert (e.g., ERROR, INFORMATION)
     */
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
