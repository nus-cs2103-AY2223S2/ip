package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        Task todo = new ToDo("play music");
        tasks.add(todo);
        assertEquals(tasks.get(0), todo);
    }

    @Test
    public void deleteTaskTest() {
        TaskList tasks = new TaskList();
        Task todo = new ToDo("play music");
        tasks.add(todo);
        assertEquals(tasks.size(), 1);
        tasks.remove(0);
        assertEquals(tasks.size(), 0);
    }
}
