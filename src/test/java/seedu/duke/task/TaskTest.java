/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test for Task class.
 */
public class TaskTest {

    /**
     * Test on converting object to String.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[ ] 123",
                new Task("123").toString());
    }

    /**
     * Test on getting the status of the task.
     */
    @Test
    public void getStatusIconSuccess() {
        assertEquals(" ",
                new Task("123").getStatusIcon());
    }

    /**
     * Test on getting the description.
     */
    @Test
    public void getDescriptionSuccess() {
        assertEquals("123",
                new Task("123").getDescription());
    }
}
