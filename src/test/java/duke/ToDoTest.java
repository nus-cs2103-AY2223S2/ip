package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void TestAdditionOfToDoToTaskList() {
        TaskList tasks = new TaskList();
        tasks.addToDo("todo test todo");
        assertEquals("1. [T][ ] test todo\n", tasks.getTaskList());
    }
}