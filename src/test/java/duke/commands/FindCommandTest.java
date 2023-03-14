package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;


public class FindCommandTest {
    @Test
    public void executeTest() throws DukeException {
        TaskList tl = new TaskList();
        Storage storage = new Storage("taskListTest.txt");
        Deadline d = new Deadline("homework",
                "2024-10-20 2359");
        tl.add(d);

        FindCommand findCommand1 = new FindCommand("find home");
        FindCommand findCommand2 = new FindCommand("find a");

        assertEquals(findCommand1.execute(tl, storage),
                "Here are the tasks in your list:\n"
                        + "    1. [D] [   ]  homework\n"
                        + " (by: Oct 20 2024 11:59 PM)\n\n");
        assertEquals(findCommand2.execute(tl, storage),
                "There are no tasks.");

    }
}
