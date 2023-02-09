package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.InvalidDateException;
import duke.tasks.Event;


public class EventTest {
    @Test
    public void toString_newEvent() throws InvalidDateException {
        Event event = new Event("Test", "2023-01-28", "2023-01-29");
        assertEquals("[E][ ] Test (from: Jan 28 2023 to: Jan 29 2023)",
                event.toString());
    }

    @Test
    public void toString_invalidDateExceptionThrown() {
        try {
            Event event = new Event("Test", "28-01-2023", "29-01-2023");
            assertEquals("[E][ ] Test (at: Sep 21 2022)", event.toString());
            fail();
        } catch (InvalidDateException e) {
            assertEquals("OOPS!!! Please key the date in the format: yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void toStorageString() throws InvalidDateException {
        Event event = new Event("Test", "2023-01-28", "2023-01-29");
        assertEquals("[E] | 0 | Test | 2023-01-28 | 2023-01-29",
                event.taskToData());
    }
}
