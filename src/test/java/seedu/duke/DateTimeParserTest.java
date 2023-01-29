package seedu.duke;

import org.junit.jupiter.api.Test;
import util.DateTimeParser;
import util.DukeException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeParserTest {
    @Test
    public void createDateTime_invalidFormat_dukeExceptionThrown() {
        assertThrows(DukeException.class, () -> {
            DateTimeParser.dateTimeParser("2020/02/12 1800");
        });
    }
}
