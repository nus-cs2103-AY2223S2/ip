package boo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testGetStatusOfTaskInString() {
        ToDo newToDoTask = new ToDo("Task 1");
        assertEquals("[T][ ] Task 1", newToDoTask.getStatusOfTaskInString());
    }

}
