package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toFileFormatTest(){
        Event event = new Event("project meeting", "2020-12-20 2000", "2020-12-25 2200");
        assertEquals("E | 0 | project meeting | 2020-12-20 2000 | 2020-12-25 2200\n", event.formatForFile());
    }

    @Test
    public void toStringTest() {
        Event event = new Event("project meeting", "2020-05-12 1800", "2020-12-25 2200");
        assertEquals("[E][ ] project meeting (from: May 12 2020 18:00 to: Dec 25 2020 22:00)", event.toString());
    }

    @Test
    public void markTest() {
        Event event = new Event("test", "2020-05-12 1800", "2020-12-25 2200");
        event.marked();
        assertEquals("[E][X] test (from: May 12 2020 18:00 to: Dec 25 2020 22:00)", event.toString());
    }
}