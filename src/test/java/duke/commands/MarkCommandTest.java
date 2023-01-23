package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkCommandTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("taskListTest.txt");
        Deadline d = new Deadline("homework",
                "2022-10-20 2359");
        tl.add(d);
        MarkCommand mc = new MarkCommand("mark 1");

        assertTrue(mc.execute(tl, ui, storage));
    }
}
