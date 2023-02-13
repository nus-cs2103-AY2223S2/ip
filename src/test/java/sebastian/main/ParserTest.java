package sebastian.main;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import sebastian.command.AddDeadlineCommand;
import sebastian.command.AddEventCommand;
import sebastian.command.AddTodoCommand;
import sebastian.command.Command;
import sebastian.command.DeleteCommand;
import sebastian.command.ExitCommand;
import sebastian.command.GetCommand;
import sebastian.command.ListCommand;
import sebastian.command.MarkCommand;
import sebastian.command.UnmarkCommand;
import sebastian.exceptions.IllegalInputException;


public class ParserTest {
    @Test
    public void testAddCommand() {
        assertAll(() -> {
            Command todo = Parser.parse("todo");
            assertTrue(todo instanceof AddTodoCommand);
        }, () -> {
            Command deadline = Parser.parse("deadline");
            assertTrue(deadline instanceof AddDeadlineCommand);
        }, () -> {
            Command event = Parser.parse("event");
            assertTrue(event instanceof AddEventCommand);
        }, () -> {
            Command mark = Parser.parse("mark");
            assertTrue(mark instanceof MarkCommand);
        }, () -> {
            Command unmark = Parser.parse("unmark");
            assertTrue(unmark instanceof UnmarkCommand);
        }, () -> {
            Command delete = Parser.parse("delete");
            assertTrue(delete instanceof DeleteCommand);
        }, () -> {
            Command exit = Parser.parse("bye");
            assertTrue(exit instanceof ExitCommand);
        }, () -> {
            Command list = Parser.parse("list");
            assertTrue(list instanceof ListCommand);
        }, () -> {
            Command get = Parser.parse("get");
            assertTrue(get instanceof GetCommand);
        });
    }

    @Test
    public void testException() {
        assertThrows(IllegalInputException.class, () -> {
            Parser.parse("some random command");
        });
    }
}
