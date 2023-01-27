package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetDescription() {
        Event event = new Event("Event Name", "1999-12-12","1999-12-12");
        assertEquals("Event Name | 1999-12-12 | 1999-12-12", event.getDescription());
    }

    @Test
    public void testGetStatus() {
        Event event = new Event("Event Name", true,"1999-12-12","1999-12-12");
        assertEquals("true", event.getStatus());
    }
}
