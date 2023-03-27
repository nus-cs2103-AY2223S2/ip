package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Test Class for testing the Deadline class.
 *
 * @author owen-yap
 */
public class DeadlineTest {
    /**
     * Test the marking of a deadline and check that isDone is true.
     */
    @Test
    public void mark_whenMarkingDeadline_thenIsDoneIsTrue() {
        LocalDateTime by = LocalDateTime.of(2023, 1, 31, 12, 0);
        Deadline deadline = new Deadline("Test deadline", by);
        deadline.mark();
        assertTrue(deadline.isDone());
    }
    /**
     * Test the status icon of a marked deadline and check that it returns 'X'.
     */
    @Test
    public void statusIcon_givenIsDoneTrue_thenReturnX() {
        LocalDateTime by = LocalDateTime.of(2023, 1, 31, 12, 0);
        Deadline deadline = new Deadline("Test deadline", by);
        deadline.mark();
        assertEquals("X", deadline.getStatusIcon());
    }
    /**
     * Test the unmarking of a deadline and check that isDone is false.
     */
    @Test
    public void unmark_whenUnmarkingDeadline_thenIsDoneIsFalse() {
        LocalDateTime by = LocalDateTime.of(2023, 1, 31, 12, 0);
        Deadline deadline = new Deadline("Test deadline", by, true);
        deadline.unmark();
        assertFalse(deadline.isDone());
    }
    /**
     * Test the toString method of the Deadline class and check that it returns the correct string.
     */
    @Test
    public void toString_whenCallingToString_thenReturnCorrectString() {
        LocalDateTime by = LocalDateTime.of(2023, 1, 31, 12, 0);
        Deadline deadline = new Deadline("Test deadline", by);
        String expected = "[D][ ] Test deadline (by: Jan 31 2023 12:00PM)";
        assertEquals(expected, deadline.toString());
    }
}
