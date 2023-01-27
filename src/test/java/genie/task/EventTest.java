package genie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testToStringConversion() {
        assertEquals("[E][ ] team meeting (from: 16/1 8pm to: 9pm)",
                new Event("team meeting", "16/1 8pm", "9pm").toString());
    }
    @Test
    public void testToFileFormat() {
        assertEquals("[E][ ] team meeting | sat 8pm - 9pm",
                new Event("team meeting", "sat 8pm", "9pm").toFileFormat());
    }
}
