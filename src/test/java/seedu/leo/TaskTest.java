package seedu.leo;
import leo.task.LeoTaskException;
import leo.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTask() {
        try {
            Task t = Task.createTask("todo test".split(" ", 2));
            assertEquals("[T][ ] test", t.toString());
        } catch (LeoTaskException e) {
            return;
        }
    }

    @Test
    public void testDeadline() {
        try {
            Task t = Task.createTask("deadline test /by 1/1/2023 0000".split(" ", 2));
            assertEquals("[D][ ] test (by: Jan 1 2023, 00:00)", t.toString());
        } catch (LeoTaskException e) {
            return;
        }
    }

    @Test
    public void testEvent() {
        try {
            Task t = Task.createTask("event test /from 1/1/2023 0000 /to 1/1/2023 2359".split(" ", 2));
            assertEquals("[E][ ] test (from: Jan 1 2023, 00:00, to: Jan 1 2023, 23:59)", t.toString());
        } catch (LeoTaskException e) {
            return;
        }
    }

    @Test
    public void testSetDone() {
        try {
            Task t = Task.createTask("todo test".split(" ", 2));
            t.setDone();
            assertEquals("[T][X] test", t.toString());
        } catch (LeoTaskException e) {
            return;
        }
    }

    @Test
    public void testSetNotDone() {
        try {
            Task t = Task.createTask("todo test".split(" ", 2));
            t.setDone();
            t.setNotDone();
            assertEquals("[T][ ] test", t.toString());
        } catch (LeoTaskException e) {
            return;
        }
    }
}
