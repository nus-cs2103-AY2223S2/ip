package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEvent() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        assertEquals("[E][ ] test (from: Jan 09 2020 12:00 to: Jan 10 2020 13:00)", event.toString());
    }

    @Test
    public void testEventDone() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        event.markAsDone();
        assertEquals("[E][X] test (from: Jan 09 2020 12:00 to: Jan 10 2020 13:00)", event.toString());
    }

    @Test
    public void testEventToFileString() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        assertEquals("E | 0 | test | 9/01/2020 1200 | 10/01/2020 1300", event.toFileString());
    }

    @Test
    public void testEventToFileStringDone() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        event.markAsDone();
        assertEquals("E | 1 | test | 9/01/2020 1200 | 10/01/2020 1300", event.toFileString());
    }

    @Test
    public void testEventGetTask() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        assertEquals("test", event.getTask());
    }

    @Test
    public void testEventGetDate() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        assertEquals("9/01/2020 1200", event.getFrom());
        assertEquals("10/01/2020 1300", event.getTo());
    }

    @Test
    public void testEventEquals() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        Event event2 = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        assertEquals(event, event2);
    }

    @Test
    public void testEventNotEquals() {
        Event event = new Event("test", "9/01/2020 1200", "10/01/2020 1300");
        Event event2 = new Event("test2", "9/01/2020 1200", "10/01/2020 1300");
        assertEquals(false, event.equals(event2));
    }
}
