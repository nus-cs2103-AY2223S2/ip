package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private final String deadline = "01012023 1200";
    private final LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("ddMMyyyy "
            + "HHmm"));

    private final String start = "01012023 1200";
    private final String end = "02012023 1800";
    private final LocalDateTime startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("ddMMyyyy "
            + "HHmm"));
    private final LocalDateTime endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));

    @Test
    public void testGetDescription() {
        Task task = new Deadline("Test deadline", deadlineDateTime);
        assertEquals("Test deadline", task.getDescription());
    }

    @Test
    public void testIsDone() {
        Task task = new Event("Test event", startDateTime, endDateTime);
        assertFalse(task.isDone());
        task.setDone(true);
        assertTrue(task.isDone());
    }

    @Test
    public void testSetDone() {
        Task task = new Event("Test event", startDateTime, endDateTime);
        assertFalse(task.isDone());
        task.setDone(true);
        assertTrue(task.isDone());
        task.setDone(false);
        assertFalse(task.isDone());
    }

    @Test
    public void testToString() {
        Task task = new Event("Test event", startDateTime, endDateTime);
        assertEquals("[E][   ] Test event (from: Jan 01 2023 12:00 to: Jan 02 2023 18:00)", task.toString());
    }
}
