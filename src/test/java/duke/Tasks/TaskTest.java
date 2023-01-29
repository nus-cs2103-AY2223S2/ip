package duke.Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void testSetIsDone() {
        Task tsk = new Task(1, "run");
        tsk.setIsDone(true);
        assertTrue(tsk.isDone);
    }

    @Test
    public void testGetStatusIconFalse() {
        Task tsk = new Task(1, "run");
        String ans = tsk.getStatusIcon();
        assertEquals(" ", ans);
    }

    @Test
    public void testGetStatusIconTrue() {
        Task tsk = new Task(1, "run");
        tsk.setIsDone(true);
        String ans = tsk.getStatusIcon();
        assertEquals("X", ans);
    }

    @Test
    public void testSetId() {
        Task tsk = new Task(1, "run");
        tsk.setId(2);
        assertEquals(2, tsk.id);
    }

    @Test
    public void testToFile() {
        Task tsk = new Task(1, "run");
        String expected = "T|run";
        assertEquals(expected, tsk.toFile());
    }

    @Test
    public void testToString() {
        Task tsk = new Task(1, "run");
        String expected = "1. [ ] run";
        assertEquals(expected, tsk.toString());
    }
}
