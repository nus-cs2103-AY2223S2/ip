package duke.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


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
