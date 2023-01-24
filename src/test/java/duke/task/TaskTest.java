package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testMarking() {
        Task example = new Task("description");
        assertEquals(" ", example.getStatusIcon());
        example.markAsDone();
        assertEquals("X", example.getStatusIcon());
        example.markAsUndone();
        assertEquals(" ", example.getStatusIcon());
    }

    @Test
    public void testStringConversion() {
        Task example = new Task("description");
        assertEquals("[ ] description", example.toString());
        example.markAsDone();
        assertEquals("[X] description", example.toString());
    }

    @Test
    public void testDataConversion() {
        Task example = new Task("description");
        assertEquals("Task/0/description", example.printData());
    }
}