package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void testEventToString() {
        assertEquals("[E][ ] project meeting (from: Jan-1-2022 14:00 to: Jan-23-2022 14:00)",
            new Event("project meeting",
                "Jan-1-2022 14:00", "Jan-23-2022 14:00", false).toString());
    }

    @Test
    public void testMarkEvent() {
        Event mockEvent = new Event("project meeting",
                "Jan-1-2022 14:00", "Jan-23-2022 14:00", false);
        mockEvent.setIsDone(true);
        assertEquals("[E][X] project meeting (from: Jan-1-2022 14:00 to: Jan-23-2022 14:00)",
                mockEvent.toString());
    }

    @Test
    public void testUnmarkEvent() {
        Event mockEvent = new Event("project meeting",
                "Jan-1-2022 14:00", "Jan-23-2022 14:00", true);
        mockEvent.setIsDone(false);
        assertEquals("[E][ ] project meeting (from: Jan-1-2022 14:00 to: Jan-23-2022 14:00)",
                mockEvent.toString());
    }
}
