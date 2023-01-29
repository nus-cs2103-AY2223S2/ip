package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getTaskTest() {
        TaskList list = new TaskList(new ArrayList<Task>());
        Task testTask = new ToDo("test");
        list.addTask(testTask);
        assertEquals(testTask, list.getTask(0));
    }

    
}
