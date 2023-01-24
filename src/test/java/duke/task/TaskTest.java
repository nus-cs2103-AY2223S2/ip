package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetDescription() throws DukeException {
        Task task = new Deadline("Test deadline", "01012023 1200");
        assertEquals("Test deadline", task.getDescription());
    }

    @Test
    public void testIsDone() throws DukeException {
        Task task = new Event("Test event", "01012023 1200", "02012023 1800");
        assertEquals(false, task.isDone());
        task.setDone(true);
        assertEquals(true, task.isDone());
    }

    @Test
    public void testSetDone() throws DukeException {
        Task task = new Event("Test event", "01012023 1200", "02012023 1800");
        assertEquals(false, task.isDone());
        task.setDone(true);
        assertEquals(true, task.isDone());
        task.setDone(false);
        assertEquals(false, task.isDone());
    }

    @Test
    public void testToString() throws DukeException {
        Task task = new Event("Test event", "01012023 1200", "02012023 1800");
        assertEquals("[E][ ] Test event (from: Jan 01 2023 12:00 to: Jan 02 2023 18:00)", task.toString());
    }
}
