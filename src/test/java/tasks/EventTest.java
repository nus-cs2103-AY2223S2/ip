package tasks;

import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void dateTest() {
        // event project meeting /from 2023-08-06 1400 /to 1600
        LocalDateTime startDate = LocalDateTime.parse("2023-08-06T14:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-08-06T16:00:00");
        Event event = new Event("Event Test", startDate, endDate);
        assertEquals("2023-08-06T14:00", event.getStartDate());
        assertEquals( "2023-08-06T16:00", event.getEndDate());
    }

    @Test
    public void eventStringTest() {
        LocalDateTime startDate = LocalDateTime.parse("2023-08-06T14:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-08-06T16:00:00");
        Event event = new Event("Event Test", startDate, endDate);

        // Test Case 1: Unmarked
        assertEquals("[E][ ] Event Test (from: 06 Aug 2023 14:00 to: 16:00)", event.toString());

        // Test Case 2: Marked
        event.markTask();
        assertEquals("[E][X] Event Test (from: 06 Aug 2023 14:00 to: 16:00)", event.toString());
    }
}
