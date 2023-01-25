package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E] [ ] 123 (from: 123 to: 123 )",
                    new Event("123","123","123").toString());
    }

    @Test
    public void getFromSuccess() {
        assertEquals("123",
                new Event("123","123","123").getFrom());
    }

    @Test
    public void getToSuccess() {
        assertEquals("123",
                new Event("123","123","123").getTo());
    }
}
