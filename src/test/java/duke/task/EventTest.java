package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = LocalDateTime.parse("2021-11-12 09:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2021-11-13 09:00", formatter);
        Task event = new Event("eat", from, to);
        assertEquals(event.toString(), "[E][ ] eat (from: Nov 12 2021 09:00 to: Nov 13 2021 09:00)");
    }

    @Test
    public void testMark() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = LocalDateTime.parse("2021-11-12 09:00", formatter);
        LocalDateTime to = LocalDateTime.parse("2021-11-13 09:00", formatter);
        Task event = new Event("eat", from, to);
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] eat (from: Nov 12 2021 09:00 to: Nov 13 2021 09:00)");
    }
}
