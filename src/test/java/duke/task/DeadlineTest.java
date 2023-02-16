package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTest {
    @Test
    public void addToDoTest() throws DukeException {
        Deadline task = new Deadline("Finish cs2107 tutorial", "2023-01-29");
        assertEquals("[D][ ] Finish cs2107 tutorial(by: Jan 29 2023)", task.toString());
    }

    @Test
    public void markTest() throws DukeException {
        Deadline task = new Deadline("Finish cs2107 tutorial", "2023-01-29");
        task.mark();
        assertEquals("[D][X] Finish cs2107 tutorial(by: Jan 29 2023)", task.toString());
    }

    @Test
    public void unmarkTest() throws DukeException {
        Deadline task = new Deadline("Finish cs2107 tutorial", "2023-01-29");
        task.mark();
        task.unmark();
        assertEquals("[D][ ] Finish cs2107 tutorial(by: Jan 29 2023)", task.toString());
    }
}
