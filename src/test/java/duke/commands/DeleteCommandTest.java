package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;


public class DeleteCommandTest {
    @Test
    public void executeTest() throws DukeException {
        TaskList tl = new TaskList();
        Storage storage = new Storage("taskListTest.txt");
        Deadline d = new Deadline("homework",
                "2024-10-20 2359");
        tl.add(d);

        DeleteCommand mc = new DeleteCommand("delete 1");
        assertEquals(mc.execute(tl, storage),
                "Got it. I've deleted this task:\n"
                        + "  [D] [   ]  homework\n"
                        + " (by: Oct 20 2024 11:59 PM)\n\n"
                        + "Now you have 0 tasks in the list.");

    }
}
