package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTest() {
        Event e = new Event("meeting", "mon 1pm", "mon 2pm");
        assertEquals("[E][ ] meeting (from: mon 1pm to: mon 2pm)", e.toString());
    }
}
