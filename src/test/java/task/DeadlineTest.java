package task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
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
