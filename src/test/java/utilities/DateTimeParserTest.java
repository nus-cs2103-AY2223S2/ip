package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class DateTimeParserTest {
    @Test
    public void dateTest1() {
        LocalDateTime dateTime = LocalDateTime.now();
        assertEquals(
                dateTime.truncatedTo(ChronoUnit.SECONDS),
                DateTimeParser.parse(dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
    }
}
