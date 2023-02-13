package henz.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testInitialisation() {
        Task task = new Event("Test", "2019-08-08 1800", "2019-08-08 2000");
        assertEquals("Test", task.getDescription());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Event("Test", "2019-08-08 1800", "2019-08-08 2000");
        task.markAsDone();
        assertEquals(true, task.getIsDone());
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new Event("Test", "2019-08-08 1800", "2019-08-08 2000");
        task.markAsDone();
        task.markAsNotDone();
        assertEquals(false, task.getIsDone());
    }
}
