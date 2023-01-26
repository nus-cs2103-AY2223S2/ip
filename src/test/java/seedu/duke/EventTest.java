package seedu.duke;

import duke.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void event_emptyDescription_exceptionThrown() {
        try {
            new Event("   ", "1", "1");
            fail();
        } catch (Exception e) {
            assertEquals("The description of an event cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void event_emptyStartEndTime_exceptionThrown() {
        try {
            new Event("desc", "  ", "  ");
            fail();
        } catch (Exception e) {
            assertEquals("The start and/or end time of an event cannot be empty.", e.getMessage());
        }
    }
}
