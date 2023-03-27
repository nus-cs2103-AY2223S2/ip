package duke;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddToDoCommand;
import duke.command.ListTasksCommand;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.ToDo;

public class ParserTest {
    @Test
    public void testParseToDo() throws DukeException {
        Parser parser = new Parser();
        assertEquals(parser.parseCommand("todo test"), new AddToDoCommand(new ToDo("test")));
    }

    @Test
    public void testParseWithoutTime() {
        Parser parser = new Parser();
        assertThrows("Please enter a date and time in the format yyyy-mm-dd hh:mm", DukeException.class, () ->
                parser.parseCommand("deadline test /by 2020-01-01")
        );
    }

    @Test
    public void testParseDeadline() throws DukeException {
        Parser parser = new Parser();
        assertEquals(
                parser.parseCommand("deadline test /by 1/01/2020 1200"),
                new AddDeadlineCommand(new Deadline("test", "1/01/2020 1200")
                )
        );
    }

    @Test
    public void testParseEventWithoutTime() {
        Parser parser = new Parser();
        assertThrows("Please enter a date and time in the format yyyy-mm-dd hh:mm", DukeException.class, () ->
                parser.parseCommand("event test /from 01/01/2020 /to 01/01/2020")
        );
    }

    @Test
    public void testParseCommand() throws DukeException {
        Parser parser = new Parser();
        assertEquals(
                parser.parseCommand("list"), new ListTasksCommand()
        );
    }

    @Test
    public void testParseInvalidCommand() {
        Parser parser = new Parser();
        assertThrows("I'm sorry, but I don't know what that means :-(", DukeException.class, () ->
                parser.parseCommand("invalid")
        );
    }

    @Test
    public void testParseInvalidCommandWithArguments() {
        Parser parser = new Parser();
        assertThrows("I'm sorry, but I don't know what that means :-(", DukeException.class, () ->
                parser.parseCommand("invalid test")
        );
    }

}
