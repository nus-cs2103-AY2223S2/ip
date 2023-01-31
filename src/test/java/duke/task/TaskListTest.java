package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void getTaskTest() {
        TaskList list = new TaskList(new ArrayList<Task>());
        Task testTask = new Todo("test");
        list.addTask(testTask);
        assertEquals(testTask, list.getTask(0));
    }

    @Test
    public void deleteTaskTest() throws DukeException {
        Task testTask = new Todo("test");
        Task dontCare = new Todo("");
        TaskList list = new TaskList(new ArrayList<Task>(Arrays.asList(dontCare, testTask, dontCare)));
        list.deleteTask(1);
        TaskList listAfterDelete = new TaskList(new ArrayList<Task>(Arrays.asList(dontCare, dontCare)));

        assertEquals(listAfterDelete, list);
    }
}
