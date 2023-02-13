package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeParserTest {
    @Test
    public void parse_hyphenDate_success() {
        try {
            assertEquals(DateTimeParser.parse("13-08-2000"), LocalDateTime.parse("2000-08-13T00:00"));
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void parse_forwardSlashDate_success() {
        try {
            assertEquals(DateTimeParser.parse("13/08/2000"), LocalDateTime.parse("2000-08-13T00:00"));
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void parse_shortFormMonth_success() {
        try {
            assertEquals(DateTimeParser.parse("3 Dec 2000"), LocalDateTime.parse("2000-12-03T00:00"));
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void parse_withTime_success() {
        try {
            assertEquals(DateTimeParser.parse("03-08-2000 16:00"), LocalDateTime.parse("2000-08-03T16:00"));
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void dateToStorageString_testDate_success() {
        try {
            LocalDate testDate = DateTimeParser.parse("13-01-2012").toLocalDate();
            assertEquals(DateTimeParser.dateToStorageString(testDate), "13-01-2012");
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    public void dateTimeToStorageString_testDateTime_success() {
        try {
            LocalDateTime testDateTime = DateTimeParser.parse("13-01-2012 : 0230")
            assertEquals(DateTimeParser.dateTimeToStorageString(testDateTime), "13-01-2012 02:30");
        } catch (DateTimeParseException e) {
            fail();
        }
    }

}
