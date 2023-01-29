package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event t = new Event("test", "2pm", "9pm");
        assertEquals("[E][ ] test (from: 2pm to: 9pm)", t.toString());
    }
}
