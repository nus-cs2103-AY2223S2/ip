package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void checkEventString(){
        Event event = new Event("event project meeting",
                "2023-01-27",
                "2023-01-28");
        assertEquals("[E][ ] event project meeting (from: Friday, January 27, 2023 to: Saturday, January 28, 2023)",
                event.toString());
    }
}
