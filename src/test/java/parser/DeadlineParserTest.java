package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import command.Command;
import command.DeadlineCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class DeadlineParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new DeadlineParser().parse("demo /by 2023-01-01");
        Command expected = new DeadlineCommand("demo", LocalDate.parse("2023-01-01"));
        assertEquals(actual, expected);
    }

    @Test
    public void shouldNotAcceptEmptyTask() {
        assertThrows(MissingArgumentException.class, () -> new DeadlineParser().parse(" /by 2023-01-01"));
    }

    @Test
    public void shouldNotAcceptMissingDeadline() {
        assertThrows(MissingArgumentException.class, () -> new DeadlineParser().parse("demo"));
    }

    @Test
    public void shouldNotAcceptEmptyDeadline() {
        assertThrows(MissingArgumentException.class, () -> new DeadlineParser().parse("demo /by"));
    }

    @Test
    public void shouldNotAcceptWrongDeadlineFormat() {
        assertThrows(InvalidArgumentException.class, () -> new DeadlineParser().parse("demo /by 01 Jan 2023"));
    }
}
