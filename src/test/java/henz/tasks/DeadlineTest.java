package henz.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testInitialisation() {
        Task task = new Deadline("Test", "2019-08-08 1800");
        assertEquals("Test", task.getDescription());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Deadline("Test", "2019-08-08 1800");
        task.markAsDone();
        assertEquals(true, task.getIsDone());
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new Deadline("Test", "2019-08-08 1800");
        task.markAsDone();
        task.markAsNotDone();
        assertEquals(false, task.getIsDone());
    }
}
