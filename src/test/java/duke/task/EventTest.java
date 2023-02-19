package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testToString() {
        Event t = new Event("test", "2pm", "9pm");
        String correctEvent = "[E][ ] test (from: 2pm to: 9pm) \npriority: MEDIUM";
        assertEquals(correctEvent, t.toString());
    }
}
