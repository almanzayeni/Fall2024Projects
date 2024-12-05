package edu.westga.cs1302.project3.view;

import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
    @FXML private MenuItem about;

    @FXML private MenuItem addPeople;
    
    @FXML private MenuItem close;

    @FXML private MenuItem loadTasks;
    
    @FXML private MenuItem saveTasks;
    
    @FXML private MenuItem addTaskMenu;
    
    @FXML private Button addTask;

    @FXML private Button removeTask;

    @FXML private ListView<Task> taskListView;
    
    private TaskViewModel viewModel;
    
    @FXML
    void initialize() {
    	  assert this.about != null : "fx:id=\"about\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.addPeople != null : "fx:id=\"addPeople\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.addTask != null : "fx:id=\"addTask\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.addTaskMenu != null : "fx:id=\"addTaskMenu\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.close != null : "fx:id=\"close\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.loadTasks != null : "fx:id=\"loadTasks\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.removeTask != null : "fx:id=\"removeTask\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.saveTasks != null : "fx:id=\"saveTasks\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.taskListView != null : "fx:id=\"taskListView\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.viewModel = new TaskViewModel();
        this.bindProperties();
        this.setUpLoadTasksMenu();
        this.setUpSaveTasksMenu();
    }
    
    private void bindProperties() {
    	this.taskListView.setItems(this.viewModel.getTasks());

        this.taskListView.setCellFactory(listView -> new javafx.scene.control.ListCell<Task>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setText(null); 
                } else {
                    setText(task.getTitle()); 
                }
            }
        });
        this.viewModel.getSelectedTask().bind(this.taskListView.getSelectionModel().selectedItemProperty());
    }
    
    private void setUpLoadTasksMenu() {
        this.loadTasks.setOnAction(event -> {
            try {
                Stage stage = (Stage) this.loadTasks.getParentPopup().getOwnerWindow();
                this.viewModel.loadTasksFromFile(stage);
            } catch (IOException | IllegalArgumentException err) {
                this.showAlert("Error", "An error occurred while loading the tasks.\n" + err.getMessage(), AlertType.ERROR);
            }
        });
    }
    
    private void setUpSaveTasksMenu() {
        this.saveTasks.setOnAction(event -> {
            try {
                Stage stage = (Stage) this.saveTasks.getParentPopup().getOwnerWindow();
                this.viewModel.saveTasksToFile(stage);
            } catch (IOException err) {
                this.showAlert("Error", "An error occurred while saving the tasks.\n" + err.getMessage(), AlertType.ERROR);
            }
        });
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
