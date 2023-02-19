package task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DukeException;


public class DeadlineTest {
    @Test
    public void testDeadline() {
        assertThrows(DukeException.class, () -> new Deadline("deadline test"));
        assertThrows(DukeException.class, () -> new Deadline("deadline test /by"));
        assertThrows(DukeException.class, () -> new Deadline("deadline"));
        assertDoesNotThrow(() -> new Deadline("deadline test /by 2022-10-22"));

    }

    @Test
    public void testGetType() throws DukeException {
        Deadline deadline = new Deadline("a deadline /by 2022-10-22");
        assertEquals("D", deadline.getType());
    }

    @Test
    public void testToString() throws DukeException {
        Deadline deadline = new Deadline("a deadline /by 2022-10-22");
        assertEquals("[D][ ] a deadline (by: Oct 22 2022)", deadline.toString());

        deadline.markDone();
        assertEquals("[D][X] a deadline (by: Oct 22 2022)", deadline.toString());
    }
}
