package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void setIsDone_true_returnTrue() {
        Task tsk = new Task("run");
        tsk.setIsDone(true);
        assertTrue(tsk.isDone);
    }

    @Test
    public void getStatusIcon_false_returnSpace() {
        Task tsk = new Task("run");
        String ans = tsk.getStatusIcon();
        assertEquals(" ", ans);
    }

    @Test
    public void getStatusIcon_true_returnX() {
        Task tsk = new Task("run");
        tsk.setIsDone(true);
        String ans = tsk.getStatusIcon();
        assertEquals("X", ans);
    }


    @Test
    public void toFile_format_returnFormatted() {
        Task tsk = new Task("run");
        String expected = "T|run";
        assertEquals(expected, tsk.toFile());
    }

    @Test
    public void toString_format_returnFormatted() {
        Task tsk = new Task("run");
        String expected = "[ ] run";
        assertEquals(expected, tsk.toString());
    }
}
