package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Event;
import org.junit.jupiter.api.Test;
public class EventTest {
    @Test
    public void testEventString() {
        Event event = new Event("project meeting", "2023-06-12", "2023-07-12", " | 12/06/2023 | 12/06/2023");
        String expected = "[E][ ] project meeting (from: Jun 12 2023 00:00 to: Jul 12 2023 23:59)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventDescription() {
        Event event = new Event("project meeting", "2023-06-12", "2023-07-12", " | 12/06/2023 | 12/06/2023");
        String expected = "project meeting";
        assertEquals(expected, event.getDescription());
    }

    @Test
    public void testEventRemarks() {
        Event event = new Event("project meeting", "2023-06-12", "2023-07-12", " | 12/06/2023 | 12/06/2023");
        String expected = " | 12/06/2023 | 12/06/2023";
        assertEquals(expected, event.getRemarks());
    }
}
