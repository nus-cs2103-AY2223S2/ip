package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DateTimeParserTest {
    @Test
    public void parse_validTestInput_success() throws DukeException {
        assertEquals(LocalDateTime.of(2025, Month.JANUARY, 31, 12, 22, 0),
                DateTimeParser.parse("2025-01-31 12:22"));
        assertEquals(LocalDateTime.of(2025, Month.JANUARY, 31, 12, 23, 0),
                DateTimeParser.parse("2025-01-31 12:23"));
    }

    @Test
    public void parse_invalidTestInput_failure() throws DukeException {
        assertNotEquals(LocalDateTime.of(2025, Month.JANUARY, 31, 12, 22, 30),
                DateTimeParser.parse("2025-01-31 12:22"));
        assertNotEquals(LocalDateTime.of(2025, Month.MARCH, 31, 12, 22, 30),
                DateTimeParser.parse("2025-01-31 12:22"));
    }
}
