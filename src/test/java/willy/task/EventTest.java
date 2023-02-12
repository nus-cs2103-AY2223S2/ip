package willy;

import willy.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventCreation() {
        assertEquals("[E][ ]event project meeting (from Mon 2pm to 4pm)",
                new Event("event project meeting ", "from Mon 2pm", " to 4pm").toString());
    }
}