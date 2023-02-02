package TaskList;

import Task.Task;
import Task.Todo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    Todo simpleTask = new Todo("Apple");
    ArrayList<Task> emptyList = new ArrayList<Task>();

    @Test
    void getListTest(){
        TaskList taskListTest = new TaskList(emptyList);
        taskListTest.addTask(new Todo("Apple"));
        emptyList.add(simpleTask);
        assertEquals(emptyList, taskListTest.getList());
    }

    @Test
    void getIndexTest(){
        TaskList taskListTest = new TaskList(emptyList);
        taskListTest.addTask(simpleTask);
        taskListTest.addTask(new Todo("Orange"));
        assertEquals(simpleTask,taskListTest.get(0));
    }

}
