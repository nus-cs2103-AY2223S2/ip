package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getters_validDeadline_stringReturned() {
        Deadline task = null;
        try {
            task = new Deadline("Essay", "2023-01-01 23:59");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(task.getBy(), "2023-01-01 23:59");
        assertEquals(task.getType(), "D");
        assertEquals(task.toString(), "[D][ ] Essay (by: Jan 01 2023 23:59)");
    }
}
