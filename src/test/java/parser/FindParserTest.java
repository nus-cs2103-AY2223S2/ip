package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.Command;
import command.FindCommand;
import dukeexeption.MissingArgumentException;

public class FindParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new FindParser().parse("demo");
        Command expected = new FindCommand("demo");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAcceptEmptyQuery() {
        assertThrows(MissingArgumentException.class, () -> new FindParser().parse(" "));
    }
}
