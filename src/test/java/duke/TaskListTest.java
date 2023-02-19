package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList tasklist = new TaskList(tasks);
        tasklist.addTask(new Todo("return book"));
        assertEquals(tasks.size(), 2);
    }

    @Test
    public void deleteTaskTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList tasklist = new TaskList(tasks);
        tasklist.delete(0);
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void getLengthTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        TaskList tasklist = new TaskList(tasks);
        tasklist.addTask(new Todo("return book"));
        assertEquals(tasklist.getLength(), 2);

    }

}
