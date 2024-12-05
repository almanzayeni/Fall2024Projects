package edu.westga.cs1302.project3.view;

import java.io.IOException;

import edu.westga.cs1302.project3.Main;
import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
    @FXML private MenuItem about;
    
    @FXML private MenuItem close;

    @FXML private MenuItem loadTasks;
    
    @FXML private MenuItem saveTasks;
    
    @FXML private Button addTask;

    @FXML private Button removeTask;

    @FXML private ListView<Task> taskListView;
    
    private TaskViewModel viewModel;
    
    @FXML
    void initialize() {
    	  assert this.about != null : "fx:id=\"about\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.addTask != null : "fx:id=\"addTask\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.close != null : "fx:id=\"close\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.loadTasks != null : "fx:id=\"loadTasks\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.removeTask != null : "fx:id=\"removeTask\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.saveTasks != null : "fx:id=\"saveTasks\" was not injected: check your FXML file 'MainWindow.fxml'.";
          assert this.taskListView != null : "fx:id=\"taskListView\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.viewModel = new TaskViewModel();
        this.bindProperties();
        this.setUpLoadTasksMenu();
        this.setUpSaveTasksMenu();
        this.setUpAddTaskButton();
        this.setUpRemoveTaskButton();
        this.setUpCloseMenuItem();
        this.setUpRightClickContextMenu();
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
    
    private void setUpAddTaskButton() {
    	this.addTask.setOnAction(
    			(event) -> {
    				FXMLLoader loader = new FXMLLoader();
    				loader.setLocation(Main.class.getResource(Main.ADDTASK_WINDOW));
    				try {
						loader.load();
	    				Parent parent = loader.getRoot();
	    				Scene scene = new Scene(parent);
	    				Stage setPropertyStage = new Stage();
	    				setPropertyStage.setTitle(Main.ADDTASK_WINDOW_TITLE);
	    				setPropertyStage.setScene(scene);
	    				setPropertyStage.initModality(Modality.APPLICATION_MODAL);
	    				
	    				AddTaskWindow propertyCodebehind = (AddTaskWindow) loader.getController();
	    				propertyCodebehind.bindToVM(this.viewModel);
	    				
	    				setPropertyStage.showAndWait();
					} catch (IOException error) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setContentText("Unable to load properties window.");
						alert.showAndWait();
					}
    			}
    	);
    }
    		
    private void setUpRemoveTaskButton() {
        this.removeTask.setOnAction(event -> {
            if (this.viewModel.getSelectedTask().get() != null) {
                this.viewModel.removeSelectedTask();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setContentText("Please select a task to remove.");
                alert.showAndWait();
            }
        });
    }
    
    private void setUpCloseMenuItem() {
        this.close.setOnAction(event -> {
            Stage stage = (Stage) this.close.getParentPopup().getOwnerWindow();
            stage.close();
        });
    }
    
    private void setUpRightClickContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem showDetails = new MenuItem("Show Details");
        showDetails.setOnAction(event -> {
            Task selectedTask = this.viewModel.getSelectedTask().get();
            if (selectedTask != null) {
                this.showAlert("Task Details", 
                    "Title: " + selectedTask.getTitle() + "\n" 
                + "Description: " + selectedTask.getDescription(),
                    Alert.AlertType.INFORMATION
                );
            }
        });
        contextMenu.getItems().add(showDetails);
        this.taskListView.setContextMenu(contextMenu);
    }
    
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
