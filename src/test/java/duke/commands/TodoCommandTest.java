package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoCommandTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("taskListTest.txt");
        TodoCommand tdc = new TodoCommand("todo test");

        assertTrue(tdc.execute(tl, ui, storage));
    }
}
