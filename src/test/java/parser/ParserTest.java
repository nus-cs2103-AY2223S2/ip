package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.parser.Parser;

public class ParserTest {

    @Test
    public void parseCommand_todoTask_addCommandReturned() {
        String input = "todo test";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new AddCommand(input);
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_deadlineTask_addCommandReturned() {
        String input = "deadline test /by 2024-01-01 01:01";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new AddCommand(input);
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_eventTask_addCommandReturned() {
        String input = "event test /from 2024-01-01 01:01 /to 2024-02-02 02:02";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new AddCommand(input);
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_deleteCommandReturned() {
        String input = "delete 1";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new DeleteCommand(1);
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_markCommandReturned() {
        String input = "mark 1";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new MarkCommand(1);
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_unmarkCommandReturned() {
        String input = "unmark 1";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new UnmarkCommand(1);
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_findCommandReturned() {
        String input = "find 1";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new FindCommand("1");
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_listCommandReturned() {
        String input = "list";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new ListCommand();
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_helpCommandReturned() {
        String input = "help";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new HelpCommand();
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

    @Test
    public void parseCommand_exitCommandReturned() {
        String input = "bye";
        Command actualCommand = new Parser().parseCommand(input);
        Command expectedCommand = new ExitCommand();
        assertEquals(expectedCommand.getClass(), actualCommand.getClass());
    }

}
