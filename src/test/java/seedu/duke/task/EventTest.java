/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test for Event class.
 */
public class EventTest {

    /**
     * Test on converting object to String.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[E] [ ] 123 (from: 123 to: 123 )",
                new Event("123", "123", "123").toString());
    }

    /**
     * Test on getting the from variable.
     */
    @Test
    public void getFromSuccess() {
        assertEquals("123",
                new Event("123", "123", "123").getFrom());
    }

    /**
     * Test on getting the to variable.
     */
    @Test
    public void getToSuccess() {
        assertEquals("123",
                new Event("123", "123", "123").getTo());
    }
}
