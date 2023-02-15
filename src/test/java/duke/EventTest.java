package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventCreation() {
        Event event = new Event("event celebration /from 3pm /to 1am");
        assertEquals("celebration", event.getDescription());
    }

    @Test
    public void testEventMark() {
        Event event = new Event("event celebration /from 3pm /to 1am");
        event.setDone();
        assertTrue(event.isCompleted());
    }

    @Test
    public void testEventUnmark() {
        Event event = new Event("event celebration /from 3pm /to 1am");
        event.setDone();
        event.setUndone();
        assertFalse(event.isCompleted());
    }
}
