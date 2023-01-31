package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_success() {
        Event event = new Event("Event", "2023-01-19 1800", "2023-01-21 2200");
        assertEquals("[E][ ] Event (from: Jan 19 2023 18:00 to: Jan 21 2023 22:00)", event.toString());
    }

    @Test
    public void toRaw_success() {
        Event event = new Event("Event", "2023-01-19 1800", "2023-01-21 2200");
        assertEquals("E | 0 | Event | 2023-01-19 1800 | 2023-01-21 2200\n", event.toRaw());
    }
}
