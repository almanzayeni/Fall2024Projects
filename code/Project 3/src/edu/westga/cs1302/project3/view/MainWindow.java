package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
    @FXML private Button addTask;

    @FXML private Button removeTask;

    @FXML private ListView<Task> taskListView;
    
    private TaskViewModel viewModel;
    
    @FXML
    void initialize() {
        assert this.taskListView != null : "fx:id=\"taskListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.addTask != null : "fx:id=\"addTaskButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.removeTask != null : "fx:id=\"removeTaskButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.viewModel = new TaskViewModel();
        this.bindProperties();
    }
    
    private void bindProperties() {
        this.taskListView.setItems(this.viewModel.getTasks().get());
        this.viewModel.getSelectedTask().bind(this.taskListView.getSelectionModel().selectedItemProperty());
    }
}
