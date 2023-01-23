package twofive.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse("2023-08-06 14:00", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2023-08-06 16:00", formatter);
        Event event = new Event("return book", startTime, endTime);
        assertEquals("[E][ ] return book (from: Sun Aug 6 2023 02:00PM to: Sun Aug 6 2023 04:00PM)", event.toString());
    }

    @Test
    public void eventFileWriteStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse("2023-08-06 14:00", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2023-08-06 16:00", formatter);
        Event event = new Event("return book", startTime, endTime);
        assertEquals("E | 0 | return book | 2023-08-06 14:00 | 2023-08-06 16:00", event.getFileWriteString());
    }

    @Test
    public void eventIsTodayTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startTime = LocalDateTime.parse("2023-08-06 14:00", formatter);
        LocalDateTime endTime = LocalDateTime.parse("2023-08-06 16:00", formatter);
        Event event = new Event("return book", startTime, endTime);
        LocalDate differentDate = LocalDate.parse("2023-01-23", dateOnlyFormatter);
        assertEquals(false, event.isToday(differentDate));
    }
}
