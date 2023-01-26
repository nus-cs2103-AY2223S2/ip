package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the behavior of Event class.
 */
public class EventTest {
    @Test
    public void testStringConversion() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals("[E][ ] school (from: 11 NOVEMBER 2024 to: 25 NOVEMBER 2024)",
                new Event("school", start, end).toString());
    }

    @Test
    public void testFormatStore() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals("E | 0 | school event | 2024/11/11 | 2024/11/25",
                new Event("school event", start, end).formatStore());
    }
}
