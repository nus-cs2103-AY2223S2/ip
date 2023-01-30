package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        assertEquals("[E][ ] finish individual project (from Jan 01 2023 at 00:00 to Aug 08 2023 at 23:59)",
        new Event("finish individual project", 0, "01-01-2023 0000", "08-08-2023 2359").toString());
    }
}