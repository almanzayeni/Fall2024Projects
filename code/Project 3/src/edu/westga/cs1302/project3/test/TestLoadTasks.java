package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileManager;
import edu.westga.cs1302.project3.model.TaskManager;

class TestLoadTasks {

    @Test
    void testLoadTasks() throws IOException {
        String filePath = "tasks_test_load.txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Complete homework: Finish the math assignment by tomorrow\n");
            writer.write("Read chapter 5: Read through the entire chapter\n");
        }
        TaskManager taskManager = TaskFileManager.loadTasks(filePath);

        assertEquals(2, taskManager.getTasks().size(), "Task list should contain 2 tasks");

        Task firstTask = taskManager.getTasks().get(0);
        assertEquals("Complete homework", firstTask.getTitle(), "First task title should be correct");
        assertEquals("Finish the math assignment by tomorrow", firstTask.getDescription(), "First task description should be correct");

        Task secondTask = taskManager.getTasks().get(1);
        assertEquals("Read chapter 5", secondTask.getTitle(), "Second task title should be correct");
        assertEquals("Read through the entire chapter", secondTask.getDescription(), "Second task description should be correct");

        File file = new File(filePath);
        assertTrue(file.delete(), "Test file should be deleted");
    }
}
