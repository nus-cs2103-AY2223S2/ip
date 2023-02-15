package botanic.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the behavior of ToDo class.
 */
public class ToDoTest {
    /**
     * Test the behaviour of toString().
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] get food",
                new ToDo("get food").toString());
    }

    /**
     * Test the behaviour of formatForStorage().
     */
    @Test
    public void testFormatStore() {
        assertEquals("T | 0 | get food",
                new ToDo("get food").formatForStorage());
    }
}
