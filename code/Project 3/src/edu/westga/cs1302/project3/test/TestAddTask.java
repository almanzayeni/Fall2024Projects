package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestAddTask {

    @Test
    void testAddTaskValid() {
        TaskManager taskManager = new TaskManager();

        Task task = new Task("Complete homework", "Finish the math assignment by tomorrow.");
        taskManager.addTask(task);

        assertTrue(taskManager.getTasks().contains(task), "The task should be added to the task list.");
    }

    @Test
    void testAddTaskNull() {
        TaskManager taskManager = new TaskManager();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskManager.addTask(null);
        });
        assertEquals("Task cannot be null", exception.getMessage());
    }

    @Test
    void testAddDuplicateTask() {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Complete homework", "Finish the math assignment by tomorrow.");
        Task task2 = new Task("Complete homework", "Finish the math assignment by tomorrow."); 

        taskManager.addTask(task1);
        taskManager.addTask(task2);  

        assertEquals(1, taskManager.getTasks().size(), "Duplicate tasks should not be added.");
        assertTrue(taskManager.getTasks().contains(task1), "Task 1 should be in the task list.");
    }

    @Test
    void testAddMultipleTasks() {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Buy groceries", "Get milk and eggs.");
        Task task2 = new Task("Call mom", "Make sure to wish mom a happy birthday.");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        assertTrue(taskManager.getTasks().contains(task1), "Task 1 should be added.");
        assertTrue(taskManager.getTasks().contains(task2), "Task 2 should be added.");
    }

}
