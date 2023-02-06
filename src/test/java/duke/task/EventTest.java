package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    String start = "01012023 1200";
    String end = "02012023 1800";
    LocalDateTime startDateTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));
    LocalDateTime endDateTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("ddMMyyyy HHmm"));

    @Test
    public void testEventConstructor() {
        // Test valid input
        Event event = new Event("Test event", startDateTime, endDateTime);
        assertEquals("Test event", event.getDescription());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), event.getStartDateTime());
        assertEquals(LocalDateTime.of(2023, 1, 2, 18, 0), event.getEndDateTime());
        assertFalse(event.isDone());
    }

    @Test
    public void testGetStartDateTime() {
        Event event = new Event("Test event", startDateTime, endDateTime);
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), event.getStartDateTime());
    }

    @Test
    public void testGetEndDateTime() {
        Event event = new Event("Test event", startDateTime, endDateTime);
        assertEquals(LocalDateTime.of(2023, 1, 2, 18, 0), event.getEndDateTime());
    }

    @Test
    public void testToString() {
        Event event = new Event("Test event", startDateTime, endDateTime);
        assertEquals("[E][ ] Test event (from: Jan 01 2023 12:00 to: Jan 02 2023 18:00)", event.toString());
    }
}
