package leo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
