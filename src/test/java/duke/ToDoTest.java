package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void TestAdditionOfToDoToTaskList() {
        TaskList tasks = new TaskList();
        tasks.addToDo("test task");
        assertEquals("1. [T][ ] test task\n", tasks.getTaskList());
    }
}