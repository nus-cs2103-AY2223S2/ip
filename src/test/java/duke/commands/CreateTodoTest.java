package duke.commands;

import duke.taskType.*;
import duke.*;
import duke.commands.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(lst.toString(),testList.toString());
    }
}