/**
 * Project name: Duke
 * @author Tan Jun Da
 * Student Number: A0234893U
 */

package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents a test for Deadline class.
 */
public class DeadlineTest {

    /**
     * Test on converting object to String.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[D] [ ] 123 (by: Dec 12 2019 )",
                    new Deadline("123", "2019-12-12").toString());
    }

    /**
     * Test on getting the by variable.
     */
    @Test
    public void getBySuccess() {
        assertEquals("2019-12-12", new Deadline("123", "2019-12-12").getBy());
    }
}
