package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testContainString() {
        Task task = new Task("this is an example task");
        assertTrue(task.containString("example"));
        assertTrue(task.containString("this is an example task"));
        assertFalse(task.containString("exampel"));
    }

    @Test
    public void testGet() {
        String description = "example to test get methods";
        Task task = new Task(description);

        task.markDone();
        assertTrue(task.getIsDone());
        assertEquals(description, task.getName());
        assertEquals("X", task.getStatusIcon());

        task.unmarkDone();
        assertFalse(task.getIsDone());
        assertEquals(" ", task.getStatusIcon());
    }
}
