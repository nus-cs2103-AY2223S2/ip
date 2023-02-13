package james;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import james.task.Task;
import james.task.TaskList;
import james.task.ToDo;

public class TaskListTest {
    @Test
    public void taskListToString_zeroTasks_success() {
        assertEquals("There are currently no tasks, please input tasks", new TaskList().taskListToString());
    }

    @Test
    public void taskListToString_nonzeroTasks_success() {
        ArrayList<Task> tasksTemp = new ArrayList<Task>(Arrays.asList(new ToDo("read book"),
                new ToDo("return book")));
        TaskList tasks = new TaskList();
        tasks.setTaskList(tasksTemp);
        assertEquals("\n 1. [T][ ] read book\n 2. [T][ ] return book", tasks.taskListToString());
    }

    @Test
    public void taskListToStoreString_nonzeroTasks_success() {
        ArrayList<Task> tasksTemp = new ArrayList<Task>(Arrays.asList(new ToDo("read book"),
                new ToDo("return book")));
        TaskList tasks = new TaskList();
        tasks.setTaskList(tasksTemp);
        assertEquals("T | 0 | read book\nT | 0 | return book", tasks.taskListToStoreString());
    }
}
