package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import command.Command;
import command.TodoCommand;
import dukeexeption.MissingArgumentException;

public class TodoParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new TodoParser().parse("demo");
        Command expected = new TodoCommand("demo");
        assertEquals(actual, expected);
    }

    @Test
    public void shouldNotAcceptEmptyTask() {
        assertThrows(MissingArgumentException.class, () -> new TodoParser().parse(" "));
    }
}
