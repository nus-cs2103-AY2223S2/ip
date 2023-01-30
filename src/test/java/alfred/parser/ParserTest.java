package alfred.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import alfred.exceptions.AlfredException;
import org.junit.jupiter.api.Test;

import alfred.command.Command;
import alfred.command.ExitCommand;



public class ParserTest {

    private final Parser parser = new Parser();

    // Should use stubs here, how to test equality of object?
    /*
    @Test
    public void testParse_byeCommand_success() throws AlfredException {
        Command exitCommand = new ExitCommand();
        assertEquals(parser.parse("bye"), exitCommand);
    }
     */
}
