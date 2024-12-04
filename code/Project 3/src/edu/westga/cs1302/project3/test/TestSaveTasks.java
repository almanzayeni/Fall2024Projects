package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskFileManager;
import edu.westga.cs1302.project3.model.TaskManager;

class TestSaveTasks {

    @Test
    void testSaveTasks() throws IOException {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Task("Complete homework", "Finish the math assignment by tomorrow"));
        taskManager.addTask(new Task("Read chapter 5", "Read through the entire chapter"));

        String filePath = "tasks_test.txt";

        TaskFileManager.saveTasks(taskManager, filePath);

        File file = new File(filePath);
        assertTrue(file.exists(), "File should exist");

        try (Scanner scanner = new Scanner(file)) {
            assertTrue(scanner.hasNextLine());
            String line1 = scanner.nextLine();
            assertEquals("Complete homework: Finish the math assignment by tomorrow", line1); 
            String line2 = scanner.nextLine();
            assertEquals("Read chapter 5: Read through the entire chapter", line2); 
        }
        file.delete();
    }
}
