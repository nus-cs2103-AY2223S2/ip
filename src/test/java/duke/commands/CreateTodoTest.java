package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;
import duke.tasktype.Todo;

public class CreateTodoTest {
    @Test
    public void operateTest() {
        TaskList lst = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");
        CreateTodo ct = new CreateTodo("todo lash");
        ct.operate(lst, ui, storage);
        TaskList testList = new TaskList();
        testList.add(new Todo("lash"));
        assertEquals(lst.toString(), testList.toString());
    }
}
