package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final String deadline = "01012023 1200";
    private final LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("ddMMyyyy "
            + "HHmm"));

    @Test
    public void testDeadlineConstructor() {
        // Test valid input
        Deadline deadline = new Deadline("Test deadline", deadlineDateTime);
        assertEquals("Test deadline", deadline.getDescription());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), deadline.getDeadline());
        assertFalse(deadline.isDone());
    }

    @Test
    public void testGetDeadline() {
        Deadline deadline = new Deadline("Test deadline", deadlineDateTime);
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), deadline.getDeadline());
    }


    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Test deadline", deadlineDateTime);
        assertEquals("[D][   ] Test deadline (by: Jan 01 2023 12:00)", deadline.toString());
    }
}
