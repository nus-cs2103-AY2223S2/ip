package leo.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTaskTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
    private final LocalDateTime dt = LocalDateTime.parse("19012023 12:00", formatter);
    private final DeadlineTask deadline = new DeadlineTask("project", dt);

    @Test
    public void testStringConversion() {
        String expected = "[D][ ] project (by: Thu, Jan 19, 12:00 pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testSaveFormat() {
        String expected = "[D][ ] project | 19012023 12:00\n";
        assertEquals(expected, deadline.saveFormat());
    }

    @Test
    public void testSameDay() {
        LocalDate date_one = LocalDate.of(2023, 1, 19);
        LocalDate date_two = LocalDate.of(2020, 1, 19);
        assertTrue(deadline.sameDay(date_one));
        assertFalse(deadline.sameDay(date_two));
    }
}
