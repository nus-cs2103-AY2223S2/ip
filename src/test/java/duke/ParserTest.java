package duke;

import command.Command;
import command.ExitCommand;
import exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testParseCommand_ExitCommand() throws DukeException {
        Parser parser = new Parser("bye");
        Command command = parser.parseCommand();
        assertTrue(command instanceof ExitCommand);
    }

}
