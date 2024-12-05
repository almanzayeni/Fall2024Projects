package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

public class TestConstructor {

    @Test
    void testValidTaskCreation() {
        Task task = new Task("Buy groceries", "Get milk, eggs, and bread.");
        assertEquals("Buy groceries", task.getTitle());
        assertEquals("Get milk, eggs, and bread.", task.getDescription());
    }

    @Test
    void testTaskCreationWithNullTitle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Valid description");
        });
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    void testTaskCreationWithEmptyTitle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "Valid description");
        });
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    void testTaskCreationWithNullDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Valid title", null);
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    void testTaskCreationWithEmptyDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Valid title", "");
        });
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

}
