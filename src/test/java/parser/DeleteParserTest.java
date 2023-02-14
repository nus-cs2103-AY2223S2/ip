package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.Command;
import command.DeleteCommand;
import dukeexeption.InvalidArgumentException;
import dukeexeption.MissingArgumentException;

public class DeleteParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new DeleteParser().parse("1");
        Command expected = new DeleteCommand(0);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAcceptEmptyIndex() {
        assertThrows(MissingArgumentException.class, () -> new DeleteParser().parse(" "));
    }

    @Test
    public void shouldNotAcceptNonInteger() {
        assertThrows(InvalidArgumentException.class, () -> new DeleteParser().parse("fail"));
    }
}
