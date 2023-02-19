package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("event project meeting /from Mon 2pm /to 4pm");
        assertEquals(event.toString(), "[E][ ] project meeting (from: Mon 2pm to: 4pm)");
    }
}
