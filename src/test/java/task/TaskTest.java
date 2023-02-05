package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Task;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTaskString() {
        Task task = new Task("test");
        String expected = "[ ] test";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testTaskRemarks() {
        Task task = new Task("test", "");
        String expected = "";
        assertEquals(expected, task.getRemarks());
    }

    @Test
    public void testTaskDescription() {
        Task task = new Task("test");
        String expected = "test";
        assertEquals(expected, task.getDescription());
    }
}