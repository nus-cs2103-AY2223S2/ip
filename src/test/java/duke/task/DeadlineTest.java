package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTest {
    @Test
    public void testDeadlineConstructor() throws DukeException {
        // Test valid input
        Deadline deadline = new Deadline("Test deadline", "01012023 1200");
        assertEquals("Test deadline", deadline.getDescription());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), deadline.getDeadline());
        assertFalse(deadline.isDone());
    }

    @Test
    public void testGetDeadline() throws DukeException {
        Deadline deadline = new Deadline("Test deadline", "01012023 1200");
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), deadline.getDeadline());
    }


    @Test
    public void testToString() throws DukeException {
        Deadline deadline = new Deadline("Test deadline", "01012023 1200");
        assertEquals("[D][ ] Test deadline (by: Jan 01 2023 12:00)", deadline.toString());
    }
}
