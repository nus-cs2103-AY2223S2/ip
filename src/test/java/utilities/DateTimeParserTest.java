package utilities;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {
    @Test
    public void dateTest1() {
        LocalDateTime dateTime = LocalDateTime.now();
        assertEquals(dateTime.truncatedTo(ChronoUnit.SECONDS), DateTimeParser.parse(dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
    }
}
