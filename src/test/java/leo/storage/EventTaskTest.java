package leo.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTaskTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
    private final LocalDateTime dtOne = LocalDateTime.parse("19012023 12:00", formatter);
    private final LocalDateTime dtTwo = LocalDateTime.parse("19012023 16:00", formatter);
    private final LocalDateTime dtThree = LocalDateTime.parse("20012023 13:00", formatter);

    @Test
    public void testSameDateStringConversion() {
        EventTask event = new EventTask("meeting", dtOne, dtTwo);
        String expected = "[E][ ] meeting (Thu, Jan 19, 12:00 pm - 04:00 pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testDifferentDateStringConversion() {
        EventTask event = new EventTask("meeting", dtOne, dtThree);
        String expected = "[E][ ] meeting (Thu, Jan 19, 12:00 pm - Fri, Jan 20, 01:00 pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testSaveFormat() {
        String expectedOne = "[E][ ] meeting | 19012023 12:00 | 19012023 16:00\n";
        EventTask eventOne = new EventTask("meeting", dtOne, dtTwo);
        String expectedTwo = "[E][ ] meeting | 19012023 12:00 | 20012023 13:00\n";
        EventTask eventTwo = new EventTask("meeting", dtOne, dtThree);
        assertEquals(expectedOne, eventOne.saveFormat());
        assertEquals(expectedTwo, eventTwo.saveFormat());
    }

    @Test
    public void testWithinDate() {
        EventTask eventOne = new EventTask("meeting", dtOne, dtTwo);
        EventTask eventTwo = new EventTask("meeting", dtOne, dtThree);
        LocalDate date = LocalDate.of(2023, 1, 20);
        assertFalse(eventOne.withinDate(date));
        assertTrue(eventTwo.withinDate(date));
    }
}
