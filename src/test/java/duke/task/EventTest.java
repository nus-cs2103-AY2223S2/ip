package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testGetDescription() throws DukeException {
        Event event = new Event("Event Name", "12/12/1999 1300", "12/12/1999 1300");
        assertEquals("Event Name | 12/12/1999 1300 | 12/12/1999 1300", event.getDescription());
    }

    @Test
    public void testGetStatus() {
        Event event = new Event("Event Name", true, "1999-12-12", "1999-12-12");
        assertEquals("true", event.getStatus());
    }
}
