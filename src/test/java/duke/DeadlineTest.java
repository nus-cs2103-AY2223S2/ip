package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void createDeadline() {
        Deadline deadline = new Deadline("Content", LocalDateTime.of(2023, 11, 13, 12, 00));
        assertNotNull(deadline);
        assertEquals(deadline.toString(), "[D][ ] deadline Content (by: 13/11/2023, 12:00 PM)");
    }

    public void changeStatusOfTodo() {
        Deadline deadline = new Deadline("Content", LocalDateTime.of(2023, 11, 13, 12, 00));
        assertEquals(deadline.getStatusIcon(), " ");
        deadline.markAsDone();
        assertEquals(deadline.getStatusIcon(), "X");
        deadline.markAsUndone();
        assertEquals(deadline.getStatusIcon(), " ");
    }
}
