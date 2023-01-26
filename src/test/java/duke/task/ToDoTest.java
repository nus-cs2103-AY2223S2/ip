package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the behavior of ToDo class.
 */
public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] get food", new ToDo("get food").toString());
    }

    @Test
    public void testFormatStore() {
        assertEquals("T | 0 | get food", new ToDo("get food").formatStore());
    }

    @Test
    public void testGetStatus() {
        ToDo task = new ToDo("get food");
        task.mark();
    }
}