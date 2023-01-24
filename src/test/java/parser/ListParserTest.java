package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import command.Command;
import command.ListCommand;

public class ListParserTest {
    @Test
    public void shouldParseCorrectly() {
        Command actual = new ListParser().parse("");
        Command expected = new ListCommand();
        assertEquals(actual, expected);
    }
}
