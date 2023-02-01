package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateTimeException;
import duke.task.Event;

public class EventTest {
    @Test
    public void eventDesc_correctDesc_noExceptionThrown() throws InvalidDateTimeException {
        assertEquals("[E][ ] project meeting (from: 2023-01-25 14:00 to: 2023-01-25 14:00)",
            new Event("project meeting",
                "25/1/2023 1400", "25/1/2023 1600", false).toString());
    }

    @Test
    public void eventDesc_wrongDay_exceptionThrown() {
        InvalidDateTimeException thrown = assertThrows(InvalidDateTimeException.class, ()
                -> new Event("project meeting", "25/1/2023 1400",
                    "25/1/2023 1600", false));
        assertEquals("OOPS!!! Invalid DateTime inputs!",
                thrown.toString());
    }
}
