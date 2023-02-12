package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
        Command command = Parser.parse("event go to party /from 01012023 0000 /to 02012023 1800");
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
        assertThrows(DukeException.class, () -> Parser.parse("delete   "));
    }

    @Test
    public void parse_validFilterCommand_returnsFilterCommand() throws DukeException {
        Command command = Parser.parse("filter hi, wow, sup");
        assertTrue(command instanceof Command.FilterCommand);
        String[] keywords = new String[] {"hi", "wow", "sup"};
        assertEquals(Arrays.asList(keywords), Arrays.asList(((Command.FilterCommand) command).getKeywords()));
    }

    @Test
    public void parse_invalidFilterCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("filter   "));
    }

    @Test
    public void parse_validFilterDateCommand_returnsFilterDateCommand() throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        Command command = Parser.parse("filterdate 01022023, 01022024");
        assertTrue(command instanceof Command.FilterDateCommand);
        LocalDate[] keywords = new LocalDate[] {LocalDate.parse("01022023", formatter), LocalDate.parse("01022024", formatter)};
        assertEquals(Arrays.asList(keywords), Arrays.asList(((Command.FilterDateCommand) command).getDates()));
    }

    @Test
    public void parse_invalidFilterDateCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("filterdate abc"));
    }

    @Test
    public void parse_validSortCommand_returnsSortCommand() throws DukeException {
        Command command = Parser.parse("sort");
        assertTrue(command instanceof Command.SortCommand);
    }

    @Test
    public void parse_validSortDateCommand_returnsSortDateCommand() throws DukeException {
        Command command = Parser.parse("sortdate");
        assertTrue(command instanceof Command.SortDateCommand);
    }

    @Test
    public void parse_validSortDoneCommand_returnsSortDoneCommand() throws DukeException {
        Command command = Parser.parse("sortdone");
        assertTrue(command instanceof Command.SortDoneCommand);
    }

    @Test
    public void parse_validSortTaskCommand_returnsSortTaskCommand() throws DukeException {
        Command command = Parser.parse("sorttask");
        assertTrue(command instanceof Command.SortTaskCommand);
    }

    @Test
    public void parse_validHelpCommand_returnsHelpCommand() throws DukeException {
        Command command = Parser.parse("help");
        assertTrue(command instanceof Command.HelpCommand);
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
