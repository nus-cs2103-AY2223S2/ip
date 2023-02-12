package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;

import task.TaskList;

public class CommandTest {
    @Test
    public void testGetIsExit() throws DukeException {
        Command exitCommand = new ExitCommand("bye", false);
        assertTrue(exitCommand.getIsExit());

        exitCommand = new ExitCommand("Bye", false);
        assertTrue(exitCommand.getIsExit());
    }

    @Test
    public void testExecute() throws DukeException {
        TaskList taskList = new TaskList();

        ExitCommand exitCommand = new ExitCommand("bye", false);
        assertEquals("Bye. Hope to see you again soon!", exitCommand.execute(taskList));

        exitCommand = new ExitCommand("Bye", false);
        assertEquals("Bye. Hope to see you again soon!", exitCommand.execute(taskList));
    }
}
