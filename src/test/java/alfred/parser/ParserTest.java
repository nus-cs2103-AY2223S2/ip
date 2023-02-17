package alfred.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import alfred.command.Command;
import alfred.command.ExitCommand;
import alfred.exceptions.AlfredException;




public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void testParse_byeCommand_success() throws AlfredException {
        Parser parser = new Parser();
        Command cmd = parser.parse("bye");
        assertTrue(cmd instanceof ExitCommand);
    }
}
