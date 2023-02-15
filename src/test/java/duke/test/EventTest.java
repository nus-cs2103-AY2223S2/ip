package duke.test;

import duke.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventString() {
        Event event = new Event("test", "2023-01-01", "2023-12-12");
        assertEquals("[E][ ] test (from: Jan 01 2023 00:00 to: Dec 12 2023 23:59)", event.toString());
    }

    @Test
    public void testEventStorageString() {
        Event event = new Event("test", "2023-01-01", "2023-12-12");
        assertEquals("E | 0 | test | 2023-01-01 0000 | 2023-12-12 2359", event.toStorageString());
    }
}
