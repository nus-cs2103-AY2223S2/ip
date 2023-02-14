package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import command.Command;
import command.OnCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class OnParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new OnParser().parse("2023-01-01");
        Command expected = new OnCommand(LocalDate.parse("2023-01-01"));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAcceptEmptyDate() {
        assertThrows(MissingArgumentException.class, () -> new OnParser().parse(" "));
    }

    @Test
    public void shouldNotAcceptWrongDateFormat() {
        assertThrows(InvalidArgumentException.class, () -> new OnParser().parse("01 Jan 2023"));
    }
}
