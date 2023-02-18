package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;
import duke.tasktype.Todo;

public class UnmarkTest {
    @Test
    public void operateTest() {
        TaskList lst = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        Todo t = new Todo("lashpi");
        TaskList testList = new TaskList();
        testList.add(t);
        t.mark();
        lst.add(t);
        Unmark u = new Unmark("unmark 1");
        u.operate(lst, ui, storage);
        assertEquals(lst.toString(), testList.toString());
    }
}
