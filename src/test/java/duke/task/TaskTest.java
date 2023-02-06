package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    String deadline = "01012023 1200";
    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));

    String start = "01012023 1200";
    String end = "02012023 1800";
    LocalDateTime startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));
    LocalDateTime endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));

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
