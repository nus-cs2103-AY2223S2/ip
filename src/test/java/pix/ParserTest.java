package pix;

import pix.commands.ByeCommand;
import pix.commands.Command;
import pix.parser.Parser;
import pix.data.MyData;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseByeTest() {
        ByeCommand expected = new ByeCommand();
        Command actual = new Parser(new MyData()).parseBye();
        assertEquals(expected.getClass(), actual.getClass());
    }
}
