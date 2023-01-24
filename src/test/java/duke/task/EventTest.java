package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    @Test
    public void testEventConstructor() throws DukeException {
        // Test valid input
        Event event = new Event("Test event", "01012023 1200", "02012023 1800");
        assertEquals("Test event", event.getDescription());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), event.getStartDateTime());
        assertEquals(LocalDateTime.of(2023, 1, 2, 18, 0), event.getEndDateTime());
        assertFalse(event.isDone());
    }

    @Test
    public void testGetStartDateTime() throws DukeException {
        Event event = new Event("Test event", "01012023 1200", "02012023 1800");
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), event.getStartDateTime());
    }

    @Test
    public void testGetEndDateTime() throws DukeException {
        Event event = new Event("Test event", "01012023 1200", "02012023 1800");
        assertEquals(LocalDateTime.of(2023, 1, 2, 18, 0), event.getEndDateTime());
    }

    @Test
    public void testToString() throws DukeException {
        Event event = new Event("Test event", "01012023 1200", "02012023 1800");
        assertEquals("[E][ ] Test event (from: Jan 01 2023 12:00 to: Jan 02 2023 18:00)", event.toString());
    }
}
