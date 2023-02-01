package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testInitialisation() {
        Task task = new Todo("Test");

        assertEquals("Test", task.getDescription());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Todo("Test");
        task.markAsDone();
        assertEquals(true, task.getIsDone());
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new Todo("Test");
        task.markAsDone();
        task.markAsNotDone();
        assertEquals(false, task.getIsDone());
    }
}
