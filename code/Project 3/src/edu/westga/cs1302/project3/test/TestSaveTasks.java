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
            assertTrue(scanner.hasNextLine(), "File should contain tasks");

            String line1 = scanner.nextLine();
            System.out.println(line1); 
            assertEquals("Complete homework:", line1);

            String description1 = scanner.nextLine();
            System.out.println(description1);
            assertEquals("Finish the math assignment by tomorrow", description1);

            String line2 = scanner.nextLine();
            System.out.println(line2); 
            assertEquals("Read chapter 5:", line2);

            String description2 = scanner.nextLine();
            System.out.println(description2);
            assertEquals("Read through the entire chapter", description2);
        }

        file.delete();  
    }
}
