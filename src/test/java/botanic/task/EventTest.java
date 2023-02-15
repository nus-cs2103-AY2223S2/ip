package botanic.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Test the behavior of Event class.
 */
public class EventTest {
    /**
     * Test the behaviour of toString().
     */
    @Test
    public void testStringConversion() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals("[E][ ] school (from: 11 NOVEMBER 2024 to: 25 NOVEMBER 2024)",
                new Event("school", start, end).toString());
    }

    /**
     * Test the behaviour of formatForStorage().
     */
    @Test
    public void testFormatStore() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate start = LocalDate.parse("2024/11/11", dtf);
        LocalDate end = LocalDate.parse("2024/11/25", dtf);
        assertEquals("E | 0 | school event | 2024/11/11 | 2024/11/25",
                new Event("school event", start, end).formatForStorage());
    }
}
