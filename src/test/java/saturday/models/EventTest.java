package saturday.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
public class EventTest {

    @Test
    public void testEventConstructor() {
        Event event = new Event("Meeting with team", "01-12-2022 10:00", "01-12-2022 12:00");
        assertNotNull(event);
    }

    @Test
    public void testEventDescription() {
        Event event = new Event("Meeting with team", "01-12-2022 10:00", "01-12-2022 12:00");
        assertEquals("Meeting with team", event.getDescription());
    }

    @Test
    public void testEventFromTime() {
        Event event = new Event("Meeting with team", "01-12-2022 10:00", "01-12-2022 12:00");
        assertEquals("2022-12-01T10:00", event.getFrom().toString());
    }

    @Test
    public void testEventToTime() {
        Event event = new Event("Meeting with team", "01-12-2022 10:00", "01-12-2022 12:00");
        assertEquals("2022-12-01T12:00", event.getTo().toString());
    }

    @Test
    public void testEventToString() {
        Event event = new Event("Meeting with team", "01-12-2022 10:00", "01-12-2022 12:00");
        assertEquals("[E][ ] Meeting with team (from: Dec 01 22 10:00 AM to: Dec 01 22 12:00 PM)", event.toString());
    }

}
