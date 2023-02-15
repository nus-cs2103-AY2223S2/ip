package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class EventTest {
    @Test
    public void testGetDescription() throws DukeException {
        Event event = new Event("Event Name", "12/12/1999 1300", "12/12/1999 1300");
        assertEquals("Event Name | 12/12/1999 1300 | 12/12/1999 1300", event.getDescription());
    }

    @Test
    public void testGetStatus() {
        Event event = new Event("Event Name", true, "12/12/1999 1300", "12/12/1999 1600");
        assertEquals("true", event.getStatus());
    }
}
