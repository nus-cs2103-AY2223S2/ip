package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void AddingTask() {
        TaskList taskList = new TaskList();
        ToDo toDo = new ToDo("test");
        taskList.addTask(toDo);
        assertEquals(taskList.isEmpty(), false);
        assertEquals(taskList.getSize(), 1);
    }
}
