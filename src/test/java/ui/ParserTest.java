package ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.DateFormatDukeException;
import duke.Duke;
import duke.DukeException;
import duke.NotRecognizedCommandDukeException;

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
        assertThrows(NotRecognizedCommandDukeException.class,
                () -> Parser.parseCommand("test", false));
        assertThrows(NotRecognizedCommandDukeException.class,
                () -> Parser.parseCommand("unknown", false));
        assertDoesNotThrow(() -> Parser.parseCommand("todo test", false));
        assertDoesNotThrow(() -> Parser.parseCommand("deadline test /by 2022-10-22", false));
    }
}
