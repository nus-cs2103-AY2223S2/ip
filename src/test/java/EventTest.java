import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static task.Task.DATE_IN_FMT_STR;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import exception.TaskParseException;
import task.Event;

public class EventTest {
    @Test
    public void mark() {
        Event event = new Event("Body", LocalDateTime.of(2023, 3, 4, 11, 35), LocalDateTime.of(2023, 3, 4, 14, 20));
        event.mark();
        assertEquals("[E][X] Body (04 Mar 2023 11:35 AM - 04 Mar 2023 02:20 PM)", event.toString());
    }

    @Test
    public void checkDate() {
        Event event = new Event("Body", LocalDateTime.of(2023, 3, 4, 11, 35), LocalDateTime.of(2023, 3, 4, 14, 20));
        assertTrue(event.isBeforeDate(LocalDateTime.of(2023, 3, 4, 11, 35)));
        assertFalse(event.isBeforeDate(LocalDateTime.of(2023, 3, 4, 11, 34)));
        assertTrue(event.isAfterDate(LocalDateTime.of(2023, 3, 4, 14, 20)));
        assertFalse(event.isAfterDate(LocalDateTime.of(2023, 3, 4, 14, 21)));
    }

    @Test
    public void parse() {
        assertDoesNotThrow(() -> {
            Event event = Event.parseArgs(
                    new String[]{"Body", "/from", "2023-03-04", "11:35", "/to", "2023-03-04", "14:20"});
            assertEquals("[E][ ] Body (04 Mar 2023 11:35 AM - 04 Mar 2023 02:20 PM)", event.toString());
            assertTrue(event.isBeforeDate(LocalDateTime.of(2023, 3, 4, 11, 35)));
            assertFalse(event.isBeforeDate(LocalDateTime.of(2023, 3, 4, 11, 34)));
            assertTrue(event.isAfterDate(LocalDateTime.of(2023, 3, 4, 14, 20)));
            assertFalse(event.isAfterDate(LocalDateTime.of(2023, 3, 4, 14, 21)));
        });
        TaskParseException ex = assertThrows(TaskParseException.class, () -> {
            Event.parseArgs(new String[]{"Body", "/from", "malformed", "/to", "2023-03-04", "14:20"});
        });
        assertEquals("\"malformed\"... is... when??!? write " + DATE_IN_FMT_STR + "!!", ex.getMessage());
    }

    @Test
    public void checkDate_parseLoad() {
        assertDoesNotThrow(() -> {
            Event event = Event.parseLoad(new String[]{"E true 1 1 1", "Body", "2023-03-04 11:35", "2023-03-04 14:20"});
            assertEquals("[E][X] Body (04 Mar 2023 11:35 AM - 04 Mar 2023 02:20 PM)", event.toString());
            assertTrue(event.isBeforeDate(LocalDateTime.of(2023, 3, 4, 11, 35)));
            assertFalse(event.isBeforeDate(LocalDateTime.of(2023, 3, 4, 11, 34)));
            assertTrue(event.isAfterDate(LocalDateTime.of(2023, 3, 4, 14, 20)));
            assertFalse(event.isAfterDate(LocalDateTime.of(2023, 3, 4, 14, 21)));
        });
    }
}
