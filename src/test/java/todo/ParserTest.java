package todo;

import duke.commands.Command;
import duke.parser.Parser;
import duke.commands.Bye;
import duke.data.MyData;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseByeTest() {
        Bye expected = new Bye();
        Command actual = new Parser(new MyData()).parseBye();
        assertEquals(expected.getClass(), actual.getClass());
    }
}
