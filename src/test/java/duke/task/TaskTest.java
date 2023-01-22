package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testTask() {
        Task task = new Task("test");

        assertEquals("test", task.getTask());
        assertFalse(task.isDone());
    }

    @Test
    public void testTaskDone() {
        Task task = new Task("test");
        task.markAsDone();

        assertEquals("test", task.getTask());
        assertTrue(task.isDone());
    }

    @Test
    public void testTaskToString() {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());
    }

    @Test
    public void testTaskDoneToString() {
        Task task = new Task("test");
        task.markAsDone();
        assertEquals("[X] test", task.toString());
    }

}
