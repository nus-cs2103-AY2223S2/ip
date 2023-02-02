package duke.test;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineString() {
        Deadline deadline = new Deadline("test", "2023-01-01");
        assertEquals("[D][ ] test (by: Jan 01 2023 23:59)", deadline.toString());
    }

    @Test
    public void testDeadlineStorageString() {
        Deadline deadline = new Deadline("test", "2023-01-01");
        assertEquals("D | 0 | test | 2023-01-01 2359", deadline.toStorageString());
    }
}
