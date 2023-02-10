package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/**
 * Contains test for DateTimeParser class.
 */
class DateTimeParserTest {

    private DateTimeParser parser = new DateTimeParser();

    @Test
    void parseDateTime_spaceSeperator_success() {
        final LocalDate eDate = LocalDate.of(2020, 2, 20);
        final LocalTime eTime = LocalTime.of(23, 12, 1);
        final LocalDateTime expected = LocalDateTime.of(eDate, eTime);

        assertEquals(expected, parser.parseDateTime("2020-02-20 23:12:01"));
    }

    @Test
    void parseDateTime_arbitrarySeperator_success() {
        final LocalDate eDate = LocalDate.of(2020, 2, 20);
        final LocalTime eTime = LocalTime.of(23, 12, 1);
        final LocalDateTime expected = LocalDateTime.of(eDate, eTime);

        assertEquals(expected, parser.parseDateTime("2020-02-20T23:12:01", 'T'));
        assertEquals(expected, parser.parseDateTime("2020-02-20!23:12:01", '!'));
        assertEquals(expected, parser.parseDateTime("2020-02-20~23:12:01", '~'));
        assertEquals(expected, parser.parseDateTime("2020-02-20/23:12:01", '/'));
    }
}
