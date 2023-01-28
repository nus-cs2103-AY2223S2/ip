package pix;

import pix.commands.Command;
import pix.parser.Parser;
import pix.commands.Bye;
import pix.data.MyData;

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
