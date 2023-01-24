package duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class LocalDateTimeUtilsTest {
    @Test
    public void inputDateTimeRegex_matchValidDateTimeWithValidFormat_matchFound() {
        String input = "04/02/2000 0420";

        Assertions.assertTrue(input.matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX));
    }

    @Test
    public void inputDateTimeRegex_matchInvalidDateTimeWithValidFormat_matchFound() {
        String input = "50/02/2000 2500";

        Assertions.assertTrue(input.matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX));
    }

    @Test
    public void inputDateTimeRegex_matchDifferentDateFormat_matchNotFound() {
        String input = "04-02-2000 0420";

        Assertions.assertFalse(input.matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX));
    }

    @Test
    public void inputDateTimeRegex_matchDifferentTimeFormat_matchNotFound() {
        String input = "04/02/2000 04:20";

        Assertions.assertFalse(input.matches(LocalDateTimeUtils.INPUT_DATE_TIME_REGEX));
    }

    @Test
    public void inputDateTimeFormatter_parseNoMissingData_successfulParse() {
        String input = "04/02/2000 0420";
        LocalDateTime expected = LocalDateTime.of(LocalDate.of(2000, 2, 4), LocalTime.of(4, 20));

        LocalDateTime actual = LocalDateTime.parse(input, LocalDateTimeUtils.INPUT_DATE_TIME_FORMATTER);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void inputDateTimeFormatter_parseDifferentDateFormat_throwsDateTimeParseException() {
        String input = "04-02-2000 0420";

        Assertions.assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime.parse(input, LocalDateTimeUtils.INPUT_DATE_TIME_FORMATTER);
        });
    }

    @Test
    public void inputDateTimeFormatter_parseDifferentTimeFormat_throwsDateTimeParseException() {
        String input = "04/02/2000 04:20";

        Assertions.assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime.parse(input, LocalDateTimeUtils.INPUT_DATE_TIME_FORMATTER);
        });
    }

    @Test
    public void outputDateTimeFormatter_formatDateTime_correctDateTimeFormat() {
        LocalDateTime input = LocalDateTime.of(LocalDate.of(2000, 2, 4), LocalTime.of(4, 20));
        String expected = "Fri Feb 04, 2000 04:20 AM";

        String actual = input.format(LocalDateTimeUtils.OUTPUT_DATE_TIME_FORMATTER);

        Assertions.assertEquals(expected, actual);
    }
}
