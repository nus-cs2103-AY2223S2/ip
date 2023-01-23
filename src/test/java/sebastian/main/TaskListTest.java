package sebastian.main;

import org.junit.jupiter.api.Test;
import sebastian.sebastianExceptions.DeadlineFormatMismatchException;
import sebastian.task.Deadline;
import sebastian.task.Task;
import sebastian.task.Todo;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testTaskList() {
        TaskList tl = new TaskList();
        assertAll(() -> {
            Task t = tl.addTodo(0, "join a cca");
            assertTrue(t instanceof Todo);
        }, () -> {
            Task t = tl.markTaskAtIndex(1);
            assertNotNull(t);
        }, () -> {
            Task t = tl.deleteTaskAtIndex(1);
            assertNotNull(t);
        });
    }

    @Test
    public void testDateParse() {
        TaskList tl = new TaskList();
        assertAll(() -> {
            assertThrows(DeadlineFormatMismatchException.class, () -> {
                tl.addDeadline(0, "random deadline", "1234567899");
            });
        }, () -> {
            assertThrows(DeadlineFormatMismatchException.class, () -> {
                tl.addDeadline(0, "random deadline", "20230101");
            });
        }, () -> {
            assertThrows(DeadlineFormatMismatchException.class, () -> {
                tl.addDeadline(0, "random deadline", "2023-01-01");
            });
        }, () -> {
            assertThrows(DeadlineFormatMismatchException.class, () -> {
                tl.addDeadline(0, "random deadline", "2023-1-1 1200");
            });
        }, () -> {
            assertThrows(DeadlineFormatMismatchException.class, () -> {
                tl.addDeadline(0, "random deadline", "2023-1-1 9999");
            });
        }, () -> {
            Task t = tl.addDeadline(0, "random deadline","2023-01-27 2359");
            assertTrue(t instanceof Deadline);
        });

    }
}
