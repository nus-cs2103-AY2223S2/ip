package alfred.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import alfred.exception.AlfredException;
import alfred.task.ToDo;
public class TaskListTest {
    @Test
    public void getTaskTest() throws AlfredException {
        TaskList tempTaskList = new TaskList(new ArrayList<>());
        ToDo testTask = new ToDo("Hello");
        tempTaskList.addTask(testTask);
        assertEquals(testTask, tempTaskList.getTask(0));
    }

    @Test
    public void markTest() throws AlfredException {
        TaskList tempTaskList = new TaskList(new ArrayList<>());
        ToDo testTask = new ToDo("Hello");
        tempTaskList.addTask(testTask);
        tempTaskList.markTask(0);
        assert(testTask.getStatusIcon() == "X");
    }
}
