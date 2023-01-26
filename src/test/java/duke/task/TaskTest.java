package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[ ] read book", new Task("read book").toString());
    }

    @Test
    public void testGetRawTask() {
        assertEquals("T ~ 0 ~ read book\n", new Task("read book").getRawTask());
    }

    @Test
    public void parseDatetime_validDatetimeFormat_success() throws DateTimeParseException {
        assertEquals(LocalDateTime.parse("2020-11-09T12:13"),
                new Task("read book").parseDatetime("2020-11-09 12:13"));
        assertEquals(LocalDateTime.parse("2000-01-31T00:13"),
                new Task("return book").parseDatetime("2000-01-31 00:13"));
    }
}