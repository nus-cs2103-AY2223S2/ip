package duke;

import duke.exception.InvalidDateTimeException;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void eventDesc_correctDesc_noExceptionThrown() throws InvalidDateTimeException {
            assertEquals("[E][ ] project meeting (from: 2023-01-25 14:00 to: 2023-01-25 14:00)",
                    Event.createEvent("project meeting /from 25/1/2023 1400 /to 25/1/2023 1600").toString());
    }

    @Test
    public void eventDesc_wrongDay_exceptionThrown()  {
        InvalidDateTimeException thrown = assertThrows(InvalidDateTimeException.class,
                () -> Event.createEvent("project meeting /from 40/1/2023 1400 /to 25/1/2023 1600"));
        assertEquals("OOPS!!! Invalid DateTime inputs!",
                thrown.toString());
    }
}
