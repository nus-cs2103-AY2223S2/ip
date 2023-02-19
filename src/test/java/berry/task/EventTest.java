package berry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import berry.exception.IncorrectDateException;

public class EventTest {
    @Test
    public void toString_checkEventTask_success() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        Event event = new Event("some event", validDate, validDate);
        assertEquals("[E][ ] some event (from: Feb 2 2022 to: Feb 2 2022)", event.toString());
    }

    @Test
    public void interpretTaskToString_eventTask_success() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        Event event = new Event("some event", validDate, validDate);
        assertEquals("E |   | some event | 2022-02-02 | 2022-02-02", event.interpretTaskToText());
    }
}
