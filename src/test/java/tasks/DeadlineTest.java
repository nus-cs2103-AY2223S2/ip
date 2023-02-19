package tasks;

import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void endDateTest() {
        // deadline return book /by 2023-02-05 1200
        LocalDateTime dateTime = LocalDateTime.parse("2023-02-05T12:00:00");
        Deadline deadline = new Deadline("Test Deadline", dateTime);
        assertEquals("2023-02-05T12:00", deadline.getByDate());

    }

    @Test
    public void deadlineStringTest() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-02-05T12:00:00");
        Deadline deadline = new Deadline("Test Deadline", dateTime);

        // Test Case 1: Unmarked
        assertEquals("[D][ ] Test Deadline (by: 05 Feb 2023 12:00)", deadline.toString());

        // Test Case 2: Marked
        deadline.markTask();
        assertEquals("[D][X] Test Deadline (by: 05 Feb 2023 12:00)", deadline.toString());
    }
}
