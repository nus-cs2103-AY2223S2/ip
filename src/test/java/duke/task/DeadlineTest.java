package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        Deadline deadline = new Deadline("test", "01/01/2020 1215");
        assertEquals("[D][ ] test (by: Jan 01 2020 12:15)", deadline.toString());
    }

    @Test
    public void testDeadlineDone() {
        Deadline deadline = new Deadline("test", "01/01/2020 1215");
        deadline.markAsDone();
        assertEquals("[D][X] test (by: Jan 01 2020 12:15)", deadline.toString());
    }

    @Test
    public void testDeadlineToFileString() {
        Deadline deadline = new Deadline("test", "01/01/2020 1215");
        assertEquals("D | 0 | test | 1/01/2020 1215", deadline.toFileString());
    }

    @Test
    public void testDeadlineToFileStringDone() {
        Deadline deadline = new Deadline("test", "01/01/2020 1215");
        deadline.markAsDone();
        assertEquals("D | 1 | test | 1/01/2020 1215", deadline.toFileString());
    }

    @Test
    public void testDeadlineGetTask() {
        Deadline deadline = new Deadline("test", "01/01/2020 1215");
        assertEquals("test", deadline.getTask());
    }

    @Test
    public void testDeadlineGetDate() {
        Deadline deadline = new Deadline("test", "15/01/2020 1215");
        assertEquals("15/01/2020 1215", deadline.getDeadline());
    }
}
