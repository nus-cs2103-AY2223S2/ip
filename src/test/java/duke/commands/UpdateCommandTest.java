package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;


public class UpdateCommandTest {
    @Test
    public void executeTest() throws DukeException {
        TaskList tl = new TaskList();
        Storage storage = new Storage("taskListTest.txt");
        Deadline d = new Deadline("homework",
                "2024-10-20 2359");
        tl.add(d);

        UpdateCommand findCommand1 = new UpdateCommand("update 1 /deadline 2023-09-12");
        UpdateCommand findCommand2 = new UpdateCommand("update 1 /description workshop");

        assertEquals(findCommand1.execute(tl, storage),
                "Got it. I've updated this task:\n"
                        + "  [D] [   ]  homework\n"
                        + " (by: Sep 12 2023)\n");
        assertEquals(findCommand2.execute(tl, storage),
                "Got it. I've updated this task:\n"
                        + "  [D] [   ]  workshop\n"
                        + " (by: Sep 12 2023)\n");


    }
}
