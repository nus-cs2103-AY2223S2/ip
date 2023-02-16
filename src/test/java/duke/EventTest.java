package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventCreation() {
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2020, 1, 2);
        Event event = new Event("Buy milk", from, to);
        assertEquals("Buy milk", event.getDescription());
        assertEquals("Jan 1 2020", event.getFrom());
        assertEquals("Jan 2 2020", event.getTo());    
    }

    @Test
    public void testEventCompletion() {
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2020, 1, 2);
        Event event = new Event("Buy milk", from, to);
        event.markAsDone();
        assertTrue(event.getIsDone());
    }
    
    @Test
    public void testEventToString() {
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2020, 1, 2);
        Event event = new Event("Buy milk", from, to);
        assertEquals("[E][ ] Buy milk (from: Jan 1 2020 to: Jan 2 2020)", event.toString());
    }

    @Test
    public void testEventToStringCompletion() {
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2020, 1, 2);
        Event event = new Event("Buy milk", from, to);
        event.markAsDone();
        assertEquals("[E][X] Buy milk (from: Jan 1 2020 to: Jan 2 2020)", event.toString());
    }
}
