package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;

public class ParserTest {
    @Test
    public void parse_correctCommand_noExceptionThrown() {
        try {
            Command todo = Parser.parse("todo run");
            assertTrue(todo instanceof AddTodoCommand);

            Command deadline = Parser.parse("deadline homework /by:19 Dec 2023 20:00");
            assertTrue(deadline instanceof AddDeadlineCommand);

            Command event = Parser.parse("event meeting /from:19 Dec 2023 20:00 /to:19 Dec 2023 20:30");
            assertTrue(event instanceof AddEventCommand);

            Command mark = Parser.parse("mark 1");
            assertTrue(mark instanceof MarkCommand);

            Command unmark = Parser.parse("unmark 1");
            assertTrue(unmark instanceof UnmarkCommand);

            Command delete = Parser.parse("delete 1");
            assertTrue(delete instanceof DeleteCommand);

            Command list = Parser.parse("list");
            assertTrue(list instanceof ListCommand);

            Command bye = Parser.parse("bye");
            assertTrue(bye instanceof ExitCommand);

            Command find = Parser.parse("find food");
            assertTrue(find instanceof FindCommand);

            Command updateTodo = Parser.parse("update 6 /name:run");
            assertTrue(updateTodo instanceof UpdateCommand);

        } catch (duke.DukeException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void parse_caseInsensitiveCommand_noExceptionThrown() {
        try {
            Command todo = Parser.parse("toDo run");
            assertTrue(todo instanceof AddTodoCommand);

            Command deadline = Parser.parse("DEADLINE homework /by:19 Dec 2023 20:00");
            assertTrue(deadline instanceof AddDeadlineCommand);

            Command event = Parser.parse("Event meeting /from:19 Dec 2023 20:00 /to:19 Dec 2023 20:30");
            assertTrue(event instanceof AddEventCommand);

            Command mark = Parser.parse("MARK 1");
            assertTrue(mark instanceof MarkCommand);

            Command unmark = Parser.parse("unmARk 1");
            assertTrue(unmark instanceof UnmarkCommand);

            Command delete = Parser.parse("Delete 1");
            assertTrue(delete instanceof DeleteCommand);

            Command list = Parser.parse("LIST");
            assertTrue(list instanceof ListCommand);

            Command bye = Parser.parse("BYE");
            assertTrue(bye instanceof ExitCommand);

            Command find = Parser.parse("finD food");
            assertTrue(find instanceof FindCommand);

            Command updateTodo = Parser.parse("uPDAte 6 /name:run");
            assertTrue(updateTodo instanceof UpdateCommand);

        } catch (duke.DukeException e) {
            System.out.println(e.toString());
        }
    }

}
