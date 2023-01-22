package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import book.task.Event;

public class EventTest {
    @Test
    public void instanceTest() {
        Event event = new Event("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")),
                LocalDateTime.parse("01/01/00-1400",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        assertEquals("[E][ ] test (from: 01 Jan (Sat) - 12:00PM to: 01 Jan (Sat) - 02:00PM)",
                event.toString());
    }
    @Test
    public void saveStringTest() {
        Event event = new Event("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")),
                LocalDateTime.parse("01/01/00-1400",
                        DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        assertEquals("E;false;test;01/01/00-1200;01/01/00-1400", event.saveString());
    }
    @Test
    public void markTest() {
        Event event = new Event("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")),
                LocalDateTime.parse("01/01/00-1400",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        event.mark();
        assertEquals("[E][X] test (from: 01 Jan (Sat) - 12:00PM to: 01 Jan (Sat) - 02:00PM)",
                event.toString());
    }
    @Test
    public void unmarkTest() {
        Event event = new Event("test", LocalDateTime.parse("01/01/00-1200",
                DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")),
                LocalDateTime.parse("01/01/00-1400",
                        DateTimeFormatter.ofPattern("dd/MM/yy-HHmm")));
        event.mark();
        event.unmark();
        assertEquals("[E][ ] test (from: 01 Jan (Sat) - 12:00PM to: 01 Jan (Sat) - 02:00PM)",
                event.toString());
    }
}
