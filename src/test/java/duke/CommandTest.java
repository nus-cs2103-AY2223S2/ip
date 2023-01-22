package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskCommand;
import duke.command.RemoveTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class CommandTest {

    @Test
    public void testAddToDoCommand() {
        Command command = new AddToDoCommand(new ToDo("test"));
        assertEquals(command.toString(), "AddToDoCommand{toDo=[T][ ] test}");
    }

    @Test
    public void testAddDeadlineCommand() {
        Command command = new AddDeadlineCommand(new Deadline("test", "1/01/2020 1200"));
        assertEquals(command.toString(), "AddDeadlineCommand{deadline=[D][ ] test (by: Jan 01 2020 12:00)}");
    }

    @Test
    public void testAddEventCommand() {
        Command command = new AddEventCommand(new Event("test", "1/01/2020 1200", "2/01/2020 1200"));
        assertEquals(command.toString(),
                "AddEventCommand{event=[E][ ] test (from: Jan 01 2020 12:00 to: Jan 02 2020 12:00)}");
    }

    @Test
    public void testDeleteCommand() {
        Command command = new RemoveTaskCommand(1);
        assertEquals(command.toString(), "RemoveTaskCommand{index=1}");
    }

    @Test
    public void testListCommand() {
        Command command = new ListTasksCommand();
        assertEquals(command.toString(), "ListTasksCommand{}");
    }

    @Test
    public void testMarkCommand() {
        Command command = new MarkTaskCommand(1);
        assertEquals(command.toString(), "MarkTaskCommand{index=1}");
    }

    @Test
    public void testUnmarkCommand() {
        Command command = new UnmarkTaskCommand(1);
        assertEquals(command.toString(), "UnmarkTaskCommand{index=1}");
    }
}
