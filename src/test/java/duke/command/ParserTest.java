package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_validTodoCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("todo finish homework");
        assertTrue(command instanceof Command.AddCommand);
        Todo task = (Todo) ((Command.AddCommand) command).getTask();
        assertEquals("finish homework", task.getDescription());
    }

    @Test
    public void parse_invalidTodoCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_validDeadlineCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("deadline finish proposal /by 01012023 1800");
        assertTrue(command instanceof Command.AddCommand);
        Deadline task = (Deadline) ((Command.AddCommand) command).getTask();
        assertEquals("finish proposal", task.getDescription());
        assertEquals(LocalDateTime.of(2023, 1, 1, 18, 0), task.getDeadline());
    }

    @Test
    public void parse_invalidDeadlineCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("deadline finish proposal /by invalid date"));
    }

    @Test
    public void parse_validEventCommand_returnsAddCommand() throws DukeException {
        Command command = Parser.parse("event go to party /from 01012023 /to 02012023 1800");
        assertTrue(command instanceof Command.AddCommand);
        Event task = (Event) ((Command.AddCommand) command).getTask();
        assertEquals("go to party", task.getDescription());
        assertEquals(LocalDateTime.of(2023, 1, 1,0,0), task.getStartDateTime());
        assertEquals(LocalDateTime.of(2023, 1, 2,18,0), task.getEndDateTime());
    }

    @Test
    public void parse_invalidEventCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("event go to party /from 02012023 /to 01012023"));
    }

    @Test
    public void parse_validListCommand_returnsListCommand() throws DukeException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof Command.ListCommand);
    }

    @Test
    public void parse_validMarkCommand_returnsMarkCommand() throws DukeException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof Command.MarkCommand);
        assertEquals(0, ((Command.MarkCommand) command).getIndex());
    }

    @Test
    public void parse_invalidMarkCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void parse_validUnmarkCommand_returnsUnmarkCommand() throws DukeException {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof Command.UnmarkCommand);
        assertEquals(1, ((Command.UnmarkCommand) command).getIndex());
    }

    @Test
    public void parse_invalidUnmarkCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("unmark abc"));
    }

    @Test
    public void parse_validDeleteCommand_returnsDeleteCommand() throws DukeException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof Command.DeleteCommand);
        assertEquals(0, ((Command.DeleteCommand) command).getIndex());

    }

    @Test
    public void parse_invalidDeleteCommand_throwsDukeException() {
        TaskList tasks = new TaskList();
        assertThrows(DukeException.class, () -> Parser.parse("delete   "));
    }

    @Test
    public void parse_validFilterCommand_returnsFilterCommand() throws DukeException {
        Command command = Parser.parse("filterdate 01012023");
        assertTrue(command instanceof Command.FilterCommand);
        assertEquals(LocalDate.of(2023,01,01), ((Command.FilterCommand) command).getObject());
    }

    @Test
    public void parse_invalidFilterCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("filterdate abc"));
    }

    @Test
    public void parse_validExitCommand_returnsExitCommand() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof Command.ExitCommand);
    }

    @Test
    public void parse_invalidCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("do something"));
    }

}
