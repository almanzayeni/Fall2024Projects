package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileManager;
import edu.westga.cs1302.project3.model.TaskManager;

class TestLoadTasks {

    @Test
    void testLoadTasks() throws IOException {
        String filePath = "tasks_test.txt";
        
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Task("Complete homework", "Finish the math assignment by tomorrow"));
        taskManager.addTask(new Task("Read chapter 5", "Read through the entire chapter"));

        TaskFileManager.saveTasks(taskManager, filePath);

        TaskManager loadedTaskManager = TaskFileManager.loadTasks(filePath);

        assertEquals(2, loadedTaskManager.getTasks().size(), "There should be 2 tasks loaded");

        Task task1 = loadedTaskManager.getTasks().get(0);
        assertEquals("Complete homework", task1.getTitle(), "The title of the first task should be 'Complete homework'");
        assertEquals("Finish the math assignment by tomorrow", task1.getDescription(), "The description of the first task is incorrect");

        Task task2 = loadedTaskManager.getTasks().get(1);
        assertEquals("Read chapter 5", task2.getTitle(), "The title of the second task should be 'Read chapter 5'");
        assertEquals("Read through the entire chapter", task2.getDescription(), "The description of the second task is incorrect");

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
