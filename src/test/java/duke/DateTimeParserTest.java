package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeParserTest {
    @Test
    public void parse_forwardSlashDate_success() {
        try {
            DateTimeParser parser = new DateTimeParser();
            assertEquals(parser.parse("13/08/2000"), LocalDateTime.parse("2000-08-13T00:00"));
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void parse_shortFormMonth_success() {
        try {
            DateTimeParser parser = new DateTimeParser();
            assertEquals(parser.parse("3 Dec 2000"), LocalDateTime.parse("2000-12-03T00:00"));
        } catch (DateTimeParseException e) {
            fail();
        }
    }
}
