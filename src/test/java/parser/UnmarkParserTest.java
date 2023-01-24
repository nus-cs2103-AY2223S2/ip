package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.Command;
import command.UnmarkCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class UnmarkParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new UnmarkParser().parse("1");
        Command expected = new UnmarkCommand(0);
        assertEquals(actual, expected);
    }

    @Test
    public void shouldNotAcceptEmptyIndex() {
        assertThrows(MissingArgumentException.class, () -> new UnmarkParser().parse(" "));
    }

    @Test
    public void shouldNotAcceptNonInteger() {
        assertThrows(InvalidArgumentException.class, () -> new UnmarkParser().parse("fail"));
    }
}
