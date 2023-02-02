package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Task;
import task.Todo;

public class TaskListTest {
    private Todo simpleTask = new Todo("Apple");
    private ArrayList<Task> emptyList = new ArrayList<Task>();

    @Test
    void getListTest() {
        TaskList taskListTest = new TaskList(emptyList);
        taskListTest.addTask(new Todo("Apple"));
        emptyList.add(simpleTask);
        assertEquals(emptyList, taskListTest.getList());
    }

    @Test
    void getIndexTest() {
        TaskList taskListTest = new TaskList(emptyList);
        taskListTest.addTask(simpleTask);
        taskListTest.addTask(new Todo("Orange"));
        assertEquals(simpleTask, taskListTest.get(0));
    }

}
