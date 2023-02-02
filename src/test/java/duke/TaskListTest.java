package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;



/**
 * Tests all public methods in TaskList
 */
public class TaskListTest {
    /**
     * Tests the creation of an empty task list with constructor.
     */
    @Test
    public void nullTaskLsitTest() {
        TaskList emptyTaskList = TaskList.ofNull();
        ArrayList<Task> actualTaskList = TaskList.getAllTasks();
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        assertEquals(expectedTaskList, actualTaskList);
    }

    /**
     * Tests the creation of a task list given an array
     */
    @Test
    public void taskListTest() {
        TaskList testedTaskList = TaskList.ofNull();
        ToDo t1 = new ToDo("valid to-do");
        Deadline d1 = new Deadline("valid deadline", "23/07/2023 1200");
        Event e1 = new Event("valid Event", "02/03/2023 1100", "02/03/1300");

        testedTaskList.loadFrom(Arrays.asList(t1, d1, e1));

        assertEquals(3, TaskList.getTaskCount());
        assertEquals(t1, testedTaskList.getTask(0));
        assertEquals(d1, testedTaskList.getTask(1));
        assertEquals(e1 , testedTaskList.getTask(2));
    }

    /**
     * Tests if a task can be successfully added to TaskList
     */
    @Test
    public void addTaskTest() {
        TaskList testedTaskList = TaskList.ofNull();
        ToDo t1 = new ToDo("valid to-do");
        Deadline d1 = new Deadline("valid deadline", "23/07/2023 1200");
        Event e1 = new Event("valid Event", "02/03/2023 1100", "02/03/1300");

        assertEquals(0, TaskList.getTaskCount());
        testedTaskList.addTask(t1);
        assertEquals(t1, testedTaskList.getTask(0));
        testedTaskList.addTask(d1);
        assertEquals(d1, testedTaskList.getTask(1));
        testedTaskList.addTask(e1);
        assertEquals(e1, testedTaskList.getTask(2));
    }

    /**
     * Tests if a task can be deleted from TaskList
     */
    @Test
    public void deleteTaskTest() {
        TaskList testedTaskList = TaskList.ofNull();
        ToDo t1 = new ToDo("valid to-do");
        Deadline d1 = new Deadline("valid deadline", "23/07/2023 1200");
        Event e1 = new Event("valid Event", "02/03/2023 1100", "02/03/1300");
        testedTaskList.addTask(t1);
        testedTaskList.addTask(d1);
        testedTaskList.addTask(e1);
        testedTaskList.deleteTask(0);
        assertEquals(false, TaskList.getAllTasks().contains(t1));
    }
}
