package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void hasCorrectDescriptionAndStatus() {
        Event event = new Event(1, "return book", "today 8pm", "tomorrow 8pm");
        assertEquals(event.description().contains("return book"), true);
        assertEquals(event.isCompleted(), false);
        event.markCompleted();
        assertEquals(event.isCompleted(), true);
    }
}
