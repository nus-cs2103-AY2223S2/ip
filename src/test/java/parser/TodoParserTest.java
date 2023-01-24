package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import command.Command;
import command.TodoCommand;

public class TodoParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new TodoParser().parse("demo");
        Command expected = new TodoCommand("demo");
        assertEquals(actual, expected);
    }
}
