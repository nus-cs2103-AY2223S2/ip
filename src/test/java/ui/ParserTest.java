package ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.DateFormatDukeException;
import duke.DukeException;
import duke.NotRecognizedCommandDukeException;

import java.time.LocalDate;

import task.Todo;

public class ParserTest {
    @Test
    public void testParseDate() throws DukeException {
        assertEquals(LocalDate.parse("2001-10-22"), Parser.parseDate("2001-10-22"));
        assertEquals(LocalDate.parse("2000-07-25"), Parser.parseDate("2000-07-25"));

        assertThrows(DateFormatDukeException.class, () -> Parser.parseDate("2022-19-22"));
        assertThrows(DateFormatDukeException.class, () -> Parser.parseDate("2022-13239-22"));
        assertThrows(DateFormatDukeException.class, () -> Parser.parseDate("2022"));
    }

    @Test
    public void testParseCommand() {
        assertThrows(DukeException.class,
                () -> Parser.parseCommand(" ", false));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("  todo", false));
        assertThrows(NotRecognizedCommandDukeException.class,
                () -> Parser.parseCommand("test", false));
        assertThrows(NotRecognizedCommandDukeException.class,
                () -> Parser.parseCommand("unknown", false));

        assertDoesNotThrow(() -> new Todo("find  "));
        assertDoesNotThrow(() -> new Todo("todo  "));
        assertDoesNotThrow(() -> Parser.parseCommand("todo test", false));
        assertDoesNotThrow(() -> Parser.parseCommand("deadline test /by 2022-10-22", false));
        assertDoesNotThrow(() -> Parser.parseCommand("deadline test   /by     2022-10-22", false));
    }
}
