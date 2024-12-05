package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestRemoveTask {

    @Test
    void testRemoveTaskValid() {
        TaskManager taskManager = new TaskManager();
        
        Task task = new Task("Complete homework", "Finish the math assignment by tomorrow.");
        taskManager.addTask(task);
        boolean removed = taskManager.removeTask(task);
        
        assertTrue(removed, "The task should have been removed.");
        assertFalse(taskManager.getTasks().contains(task), "The task should no longer be in the task list.");
    }

    @Test
    void testRemoveTaskNotExist() {
        TaskManager taskManager = new TaskManager();

        Task task = new Task("Complete homework", "Finish the math assignment by tomorrow.");
        boolean removed = taskManager.removeTask(task);

        assertFalse(removed, "The task should not have been removed.");
    }

    @Test
    void testRemoveTaskNull() {
        TaskManager taskManager = new TaskManager();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskManager.removeTask(null);
        });
        assertEquals("Task cannot be null", exception.getMessage());
    }

}
