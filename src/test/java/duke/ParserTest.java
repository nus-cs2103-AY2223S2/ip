package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UndoCommand;
import command.UnmarkCommand;

/**
 * Driver to test the parsing commands of Parser.
 */
public class ParserTest {
    /**
     * Tests for correct parsing of valid commands.
     */
    @Test
    public void parse_validCommands() {
        /* Test Case 1: add event */
        try {
            Command addTodo = Parser.parseUserCommand("todo task 1");
            assertEquals(AddCommand.class, addTodo.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 2: add deadline */
        try {
            Command addDeadline = Parser.parseUserCommand("deadline task 2 /by 2023-01-22 1600");
            assertEquals(AddCommand.class, addDeadline.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 3: add event */
        try {
            Command addEvent = Parser.parseUserCommand("event task 3 /from 2023-1-2 0000 /to 2023-1-2 1200");
            assertEquals(AddCommand.class, addEvent.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 4: delete task */
        try {
            Command deleteTask = Parser.parseUserCommand("delete 0");
            assertEquals(DeleteCommand.class, deleteTask.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 5: mark task */
        try {
            Command markTask = Parser.parseUserCommand("mark 0");
            assertEquals(MarkCommand.class, markTask.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 6: unmark task */
        try {
            Command unmarkTask = Parser.parseUserCommand("unmark 0");
            assertEquals(UnmarkCommand.class, unmarkTask.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 7: list tasks */
        try {
            Command listTasks = Parser.parseUserCommand("list");
            assertEquals(ListCommand.class, listTasks.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 8: find tasks */
        try {
            Command findTasks = Parser.parseUserCommand("find query");
            assertEquals(FindCommand.class, findTasks.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 9: undo previous command */
        try {
            Command undoPreviousCommand = Parser.parseUserCommand("undo");
            assertEquals(UndoCommand.class, undoPreviousCommand.getClass());
        } catch (DukeException e) {
            fail();
        }

        /* Test Case 10: end chat session */
        try {
            Command endChat = Parser.parseUserCommand("bye");
            assertEquals(ExitCommand.class, endChat.getClass());
        } catch (DukeException e) {
            fail();
        }
    }

    /**
     * Tests for correct parsing of invalid commands.
     */
    @Test
    public void parse_invalidCommands() {
        /* Test Case 1: missing task name */
        try {
            Parser.parseUserCommand("todo");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 2: missing /by */
        try {
            Parser.parseUserCommand("deadline task 1");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 3: empty /from and /to */
        try {
            Parser.parseUserCommand("event task 2 /from /to");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 4: unsupported datetime string format */
        try {
            Parser.parseUserCommand("deadline task 3 /by 01/02/2023 1600");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 5: unsupported datetime string format */
        try {
            Parser.parseUserCommand("deadline task 3 /by 2023-01-02 4.00pm");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 6: missing task no */
        try {
            Parser.parseUserCommand("delete ");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 7: missing task no */
        try {
            Parser.parseUserCommand("mark");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 8: missing query string */
        try {
            Parser.parseUserCommand("find ");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }

        /* Test Case 9: totally unrecognised command */
        try {
            Parser.parseUserCommand("add task 1");
            fail();
        } catch (DukeException e) {
            return; /* passed */
        }
    }
}
