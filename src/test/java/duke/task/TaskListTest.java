package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;

public class TaskListTest {

    //test setup
    Task dummyTask1 = new Task("task1");
    Task dummyTask2 = new Task("task2");
    ArrayList<Task> taskList = new ArrayList<Task>();

    @Test
    public void mark() {
        taskList.add(dummyTask1);
        TaskList tl = new TaskList(taskList);
        tl.mark(1);
        String expected = "[X] task1";
        String actual = tl.getTask(0).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void unmark() {
        TaskList tl = new TaskList(taskList);
        tl.add(dummyTask1);
        tl.mark(1);
        tl.unmark(1);
        String expected = "[ ] task1";
        String actual = tl.getTask(0).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void add() {
        TaskList tl = new TaskList(taskList);
        tl.add(dummyTask1);
        String expected = "[ ] task1";
        String actual = tl.getTask(0).getStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void remove() {
        TaskList tl = new TaskList(taskList);
        tl.add(dummyTask1);
        tl.add(dummyTask2);
        String expected = "[ ] task2";
        String actual = tl.getTask(1).getStatus();
        assertEquals(expected, actual);
    }
}