package tunabot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void instanceTest() {
        Task task = new Task("Test Task");
        assertEquals("[T][ ] Test Task", task.toString());
    }
    @Test
    public void markTest() {
        Task markTask = new Task("Mark Task");
        markTask.markDone();
        assertTrue(markTask.getDone());
    }
    @Test
    public void unmarkTest() {
        Task unmarkTask = new Task("Unmark Task");
        unmarkTask.markDone();
        unmarkTask.unmarkDone();
        assertFalse(unmarkTask.getDone());
    }
}
