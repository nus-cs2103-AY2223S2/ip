package duke;

import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


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