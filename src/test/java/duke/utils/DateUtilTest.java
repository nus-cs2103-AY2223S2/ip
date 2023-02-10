package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateUtilTest {
    @Test
    void testDateToString() {
        LocalDateTime expected = LocalDateTime.of(2000, Month.JANUARY, 1, 1, 1);
        assertEquals(expected.format(DateTimeFormatter.ISO_DATE_TIME), DateUtil.dateToString(expected));
    }

    @Test
    void testToLocalDateTime() {
        LocalDateTime expected = LocalDateTime.of(2000, Month.JANUARY, 1, 1, 1);
        assertEquals(expected, DateUtil.toLocalDateTime("01/01/2000 0101"));
    }

    @Test
    void testDateToPrettyString() {
        LocalDateTime expected = LocalDateTime.of(2000, Month.JANUARY, 1, 1, 1);
        assertEquals("Jan 1 2000 1:1", DateUtil.dateToPrettyString(expected));

        expected = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0);
        assertEquals("Jan 1 2000", DateUtil.dateToPrettyString(expected));
    }
}
