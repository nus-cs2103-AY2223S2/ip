package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class DateTimeParserTest {
    @Test
    public void parse_validTestInput_success() {
        assertEquals(LocalDateTime.of(2023, Month.JANUARY, 31, 12, 22, 0),
                DateTimeParser.parse("2023-01-31 12:22"));
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 31, 12, 23, 0),
                DateTimeParser.parse("2024-01-31 12:23"));
    }

    @Test
    public void parse_invalidTestInput_failure() {
        assertNotEquals(LocalDateTime.of(2023, Month.JANUARY, 31, 12, 22, 30),
                DateTimeParser.parse("2023-01-31 12:22"));
        assertNotEquals(LocalDateTime.of(2023, Month.MARCH, 31, 12, 22, 30),
                DateTimeParser.parse("2023-01-31 12:22"));
    }
}
