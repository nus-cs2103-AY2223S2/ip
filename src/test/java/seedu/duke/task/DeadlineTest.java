/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.duke.DukeException;

/**
 * Represents a test for Deadline class.
 */
public class DeadlineTest {

    /**
     * Test on converting object to String.
     */
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[D] [ ] 123 (by: Dec 12 2019 )",
                    new Deadline("123", "2019-12-12").toString());
    }

    /**
     * Test on getting the by variable.
     */
    @Test
    public void getBySuccess() throws DukeException {
        assertEquals("2019-12-12", new Deadline("123", "2019-12-12").getBy());
    }
}
