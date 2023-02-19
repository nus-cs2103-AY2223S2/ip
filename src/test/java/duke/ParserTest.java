package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.command.Command;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTest() {
        String fullCommand = "todo play music";
        Command command = Parser.parse(fullCommand);
        assertEquals(command.getClass().getSimpleName(), "AddCommand");

        fullCommand = "bye";
        command = Parser.parse(fullCommand);
        assertEquals(command.getClass().getSimpleName(), "ByeCommand");

        fullCommand = "delete 1";
        command = Parser.parse(fullCommand);
        assertEquals(command.getClass().getSimpleName(), "DeleteCommand");

        fullCommand = "list";
        command = Parser.parse(fullCommand);
        assertEquals(command.getClass().getSimpleName(), "ListCommand");

        fullCommand = "mark 1";
        command = Parser.parse(fullCommand);
        assertEquals(command.getClass().getSimpleName(), "MarkCommand");

        fullCommand = "unmark 1";
        command = Parser.parse(fullCommand);
        assertEquals(command.getClass().getSimpleName(), "UnmarkCommand");
    }
}
