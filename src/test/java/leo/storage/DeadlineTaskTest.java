package leo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

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
        LocalDate dateOne = LocalDate.of(2023, 1, 19);
        LocalDate dateTwo = LocalDate.of(2020, 1, 19);
        assertTrue(deadline.sameDay(dateOne));
        assertFalse(deadline.sameDay(dateTwo));
    }
}
