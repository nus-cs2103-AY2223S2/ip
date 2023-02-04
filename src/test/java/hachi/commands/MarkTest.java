package hachi.commands;

import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Todo;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MarkTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("markTest.txt");
        Todo td = new Todo("task");
        tl.add(td);
        MarkCommand m = new MarkCommand("mark 1");
        assertTrue(m.execute(tl, ui, storage));
    }
}
