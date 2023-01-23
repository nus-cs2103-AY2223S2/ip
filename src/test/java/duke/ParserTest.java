package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parser_validToDo_correctCommand() throws DukeException {
        String name = "sample name";
        String rawCommand = String.format("todo %s", name);
        Command actualCommand = Parser.parse(rawCommand);
        Command expectedCommand = new ToDoCommand(name);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parser_validDeadline_correctCommand() throws DukeException {
        String name = "sample name";
        String date = "01-02-2003";
        String rawCommand = String.format("deadline %s /by %s", name, date);
        Command actualCommand = Parser.parse(rawCommand);
        Command expectedCommand = new DeadlineCommand(name, date);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parser_validEvent_correctCommand() throws DukeException {
        String name = "sample name";
        String start = "01-02-2003";
        String end = "02-02-2003";
        String rawCommand = String.format("event %s /from %s /to %s", name, start, end);
        Command actualCommand = Parser.parse(rawCommand);
        Command expectedCommand = new EventCommand(name, start, end);
        assertEquals(expectedCommand, actualCommand);
    }

}
