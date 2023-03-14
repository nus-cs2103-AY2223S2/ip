package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;



public class UnmarkCommandTest {
    @Test
    public void executeTest() throws DukeException {
        TaskList tl = new TaskList();
        Storage storage = new Storage("taskListTest.txt");
        Deadline d = new Deadline("homework",
                "2024-10-20 2359");
        tl.add(d);

        UnmarkCommand unmarkCommand1 = new UnmarkCommand("unmark 1");
        UnmarkCommand unmarkCommand2 = new UnmarkCommand("unmark ");
        UnmarkCommand unmarkCommand3 = new UnmarkCommand("unmark -1");

        assertEquals(unmarkCommand1.execute(tl, storage),
                "OK, I've marked this task as not done yet:\n"
                        + "  [D] [   ]  homework\n"
                        + " (by: Oct 20 2024 11:59 PM)\n");
        try {
            unmarkCommand2.execute(tl, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "Command must be followed by an integer.");
        }
        try {
            unmarkCommand3.execute(tl, storage);
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "There are insufficient tasks.");
        }

    }
}
