/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a test for Todo class.
 */
public class TodoTest {

    /**
     * Test on converting object to String.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[T] [ ] 123", new Todo("123").toString());
    }
}
