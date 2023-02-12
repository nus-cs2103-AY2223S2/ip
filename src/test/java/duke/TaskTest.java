package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void testTaskDescription() {
        Task task = new Task("Borrow book");
        assertEquals("Borrow book", task.getDescription());
    }

    @Test
    public void testTaskStatus() {
        Task task = new Task("Borrow book", true);
        assertEquals(true, task.getStatusIcon().equals(Parser.MARK_SYMBOL));
    }

    @Test
    public void testTaskMarkAsDone() {
        Task task = new Task("Borrow book");
        task = task.markAsDone();
        assertEquals(true, task.getStatusIcon().equals(Parser.MARK_SYMBOL));
    }

    @Test
    public void testTaskMarkAsUndone() {
        Task task = new Task("Borrow book", true);
        task = task.markAsUndone();
        assertEquals(false, task.getStatusIcon().equals(Parser.MARK_SYMBOL));
    }

    @Test
    public void testTaskToString() {
        Task task = new Task("Borrow book", true);
        assertEquals("[X] Borrow book", task.toString());
    }

}
