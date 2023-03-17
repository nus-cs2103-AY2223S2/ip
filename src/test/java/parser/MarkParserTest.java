package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.Command;
import command.MarkCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class MarkParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new MarkParser().parse("1");
        Command expected = new MarkCommand(0);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAcceptEmptyIndex() {
        assertThrows(MissingArgumentException.class, () -> new MarkParser().parse(" "));
    }

    @Test
    public void shouldNotAcceptNonInteger() {
        assertThrows(InvalidArgumentException.class, () -> new MarkParser().parse("fail"));
    }
}
