package tunabot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void instanceTest() {
        Deadline task = new Deadline("Test Task ", "01/01/23-1800");
        assertEquals("[D][ ] Test Task (by 01/01/23-1800)", task.toString());
    }

    @Test
    public void markTest() {
        Deadline markTask = new Deadline("Mark Task", "01/01/23-1800");
        markTask.markDone();
        assertTrue(markTask.getDone());
    }

    @Test
    public void unmarkTest() {
        Deadline unmarkTask = new Deadline("Unmark Task", "01/01/23-1800");
        unmarkTask.markDone();
        unmarkTask.unmarkDone();
        assertFalse(unmarkTask.getDone());
    }
}
