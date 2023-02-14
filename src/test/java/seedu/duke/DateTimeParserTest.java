package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import util.DateTimeParser;
import util.DukeException;



public class DateTimeParserTest {
    @Test
    public void createDateTime_invalidFormat_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> {
            DateTimeParser.dateTimeParser("2020/02/12 1800");
        });
    }
}
