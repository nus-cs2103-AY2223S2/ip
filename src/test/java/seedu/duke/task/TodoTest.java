/**
 * Project name: Duke
 * @author Tan Jun Da
 * Student Number: A0234893U
 */

package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
