package duke.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeUtils;
import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.RemoveCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeRuntimeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

public class ParserTest {

    private static final Class<DukeRuntimeException> exceptionClass = DukeRuntimeException.class;

    @Test
    public void shouldCorrectlyParseSomeCommand() {
        assertEquals(Parser.parseCommand("list"), new ListCommand());
        assertEquals(Parser.parseCommand("bye"), new ExitCommand());
        assertEquals(Parser.parseCommand("todo eat"), new AddCommand(new TodoTask("eat")));
        assertEquals(Parser.parseCommand("deadline homework --by 2023-01-01"), new AddCommand(
                new DeadlineTask("homework", DukeUtils.convertStringToDate("2023-01-01").get())));
        assertEquals(Parser.parseCommand("event live --from current --to future"),
                new AddCommand(new EventTask("live", "current", "future")));
        assertEquals(Parser.parseCommand("mark 10"), new MarkCommand(10));
        assertEquals(Parser.parseCommand("unmark 10"), new UnmarkCommand(10));
        assertEquals(Parser.parseCommand("delete 10"), new RemoveCommand(10));
        assertEquals(Parser.parseCommand("find money"), new FindCommand("money"));
    }

    @Test
    public void shouldThrowForEmptyDescription() {
        DukeRuntimeException ex;
        ex = assertThrows(exceptionClass, () -> Parser.parseCommand("todo"));
        assertTrue(ex.getMessage().contains("description cannot be empty"));
        ex = assertThrows(exceptionClass, () -> Parser.parseCommand("deadline --by 2023-01-01"));
        assertTrue(ex.getMessage().contains("description cannot be empty"));
        ex = assertThrows(exceptionClass,
                () -> Parser.parseCommand("event --from current --to future"));
        assertTrue(ex.getMessage().contains("description cannot be empty"));
    }

    @Test
    public void shouldThrowForInvalidIntArgument() {
        DukeRuntimeException ex;
        ex = assertThrows(exceptionClass, () -> Parser.parseCommand("mark 10v"));
        assertTrue(ex.getMessage().contains("expect an integer as argument"));
        ex = assertThrows(exceptionClass, () -> Parser.parseCommand("delete 10v"));
        assertTrue(ex.getMessage().contains("expect an integer as argument"));
    }
}
