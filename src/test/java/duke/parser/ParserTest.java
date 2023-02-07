package duke.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseDateTime_validTestInput_success() {
        assertEquals(LocalDateTime.of(2023, Month.JANUARY, 31, 12, 22, 0),
                Parser.parseDateTime("2023-01-31 12:22"));
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 31, 12, 23, 0),
                Parser.parseDateTime("2024-01-31 12:23"));
    }

    @Test
    public void parseDateTime_invalidTestInput_failure() {
        assertNotEquals(LocalDateTime.of(2023, Month.JANUARY, 31, 12, 22, 30),
                Parser.parseDateTime("2023-01-31 12:22"));
        assertNotEquals(LocalDateTime.of(2023, Month.MARCH, 31, 12, 22, 30),
                Parser.parseDateTime("2023-01-31 12:22"));
    }
}
