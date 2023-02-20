package voile.util.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import voile.common.exception.VoileRuntimeException;
import voile.model.command.AddCommand;
import voile.model.command.Command;
import voile.model.command.ExitCommand;
import voile.model.command.FindCommand;
import voile.model.command.ListCommand;
import voile.model.command.MarkCommand;
import voile.model.command.RemoveCommand;
import voile.model.command.UnmarkCommand;
import voile.model.task.DeadlineTask;
import voile.model.task.EventTask;
import voile.model.task.TodoTask;
import voile.util.Conversions;

public class CommandParserTest {
    private static final Class<VoileRuntimeException> EXPECTED_EXCEPTION_CLASS =
            VoileRuntimeException.class;

    private Command parseCommand(String input) {
        return CommandParser.of(input).parse();
    }

    @Test
    public void parseCommand_validInput_shouldReturn() {
        assertEquals(parseCommand("list"), new ListCommand());
        assertEquals(parseCommand("bye"), new ExitCommand());
        assertEquals(parseCommand("todo eat"), new AddCommand(new TodoTask("eat")));
        assertEquals(parseCommand("deadline homework --by 2023-01-01"),
                new AddCommand(new DeadlineTask(
                        "homework",
                        Conversions.parseDate("2023-01-01").get())));
        assertEquals(parseCommand("event survive --from 2023-01-01 --to 2023-10-10"),
                new AddCommand(new EventTask(
                        "survive",
                        Conversions.parseDate("2023-01-01").get(),
                        Conversions.parseDate("2023-10-10").get())));
        assertEquals(parseCommand("mark 10"), new MarkCommand(10));
        assertEquals(parseCommand("unmark 10"), new UnmarkCommand(10));
        assertEquals(parseCommand("delete 10"), new RemoveCommand(10));
        assertEquals(parseCommand("find money"), new FindCommand("money"));
    }

    @Test
    public void parseCommand_emptyDescription_shouldThrow() {
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parseCommand("todo"));
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> parseCommand("deadline --by 2023-01-01"));
        assertThrows(EXPECTED_EXCEPTION_CLASS,
                () -> parseCommand("event --from 2023-01-01 --to 2023-10-10"));

    }

    @Test
    public void parseCommand_invalidInt_shouldThrow() {
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parseCommand("mark 10v"));
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parseCommand("delete 10v"));
    }
}
