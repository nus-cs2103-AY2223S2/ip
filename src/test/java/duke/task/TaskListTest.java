package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.DukeException;

/**
 * Tester for TaskList class.
 */
public class TaskListTest {
    @Test
    public void getListTest() {
        List<Task> testList = new ArrayList<Task>();
        TaskList tasklist = new TaskList(testList);
        List<Task> returnedList = tasklist.getList();
        assertEquals(testList, returnedList);
    }

    @Test
    public void addTaskTest() {
        Task taskToAdd = new Todo("To add");
        List<Task> listOfTasks = new ArrayList<Task>();

        // Add task to the task list
        TaskList testList = new TaskList(listOfTasks);
        testList.addTask(taskToAdd);

        assertEquals(taskToAdd, listOfTasks.get(0));
    }

    @Test
    public void getTaskTest() {
        Task testTask = new Todo("test");
        TaskList list = new TaskList(new ArrayList<Task>(Arrays.asList(testTask)));
        assertEquals(testTask, list.getTask(0));
    }

    @Test
    public void deleteTaskTest() throws DukeException {
        Task taskToDelete = new Todo("test");
        TaskList listOfTasks = new TaskList(new ArrayList<Task>(Arrays.asList(taskToDelete)));

        listOfTasks.listTasks(); // To update the indexToTask hashmap

        Task taskDeleted = listOfTasks.deleteTask(0);
        TaskList listAfterDelete = new TaskList(new ArrayList<Task>());

        assertEquals(listAfterDelete, listOfTasks);
        assertEquals(taskToDelete, taskDeleted);
    }
}
