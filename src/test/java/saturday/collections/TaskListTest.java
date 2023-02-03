package saturday.collections;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import saturday.models.Deadline;
import saturday.models.Event;
import saturday.models.Task;
import saturday.models.ToDo;
public class TaskListTest {
    private TaskList taskList;

    @Before
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testMark() {
        setUp();
        taskList.add(new ToDo("Test task"));
        taskList.mark(1);
        Assertions.assertTrue(taskList.get(1).getIsDone());
    }

    @Test
    public void testUnMark() {
        setUp();
        taskList.add(new ToDo("Test task"));
        taskList.mark(1);
        taskList.unMark(1);
        Assertions.assertFalse(taskList.get(1).getIsDone());
    }

    @Test
    public void testGetTaskListOnDate() {
        setUp();
        taskList.add(new Deadline("Test deadline", "01-01-2022 12:00"));
        taskList.add(new Event("Test event", "01-01-2022 12:00", "01-01-2022 14:00"));
        taskList.add(new ToDo("Test todo"));

        TaskList tasksOnDate = taskList.getTaskListOnDate("01-01-2022");
        Assertions.assertEquals(2, tasksOnDate.size());
        Assertions.assertTrue(tasksOnDate.get(1) instanceof Deadline || tasksOnDate.get(1) instanceof Event);
        Assertions.assertTrue(tasksOnDate.get(2) instanceof Deadline || tasksOnDate.get(2) instanceof Event);
    }

    @Test
    public void testRemove() {
        setUp();
        taskList.add(new ToDo("Test Task 1"));
        taskList.add(new ToDo("Test Task 2"));
        taskList.add(new ToDo("Test Task 3"));

        // Test removing from middle of list
        Task removedTask = taskList.remove(2);
        Assertions.assertEquals("Test Task 2", removedTask.getDescription());
        Assertions.assertEquals(2, taskList.size());
        Assertions.assertEquals("1.[T][ ] Test Task 1\n\t2.[T][ ] Test Task 3\t", taskList.toString());

        // Test removing from end of list
        removedTask = taskList.remove(2);
        Assertions.assertEquals("Test Task 3", removedTask.getDescription());
        Assertions.assertEquals(1, taskList.size());
        Assertions.assertEquals("1.[T][ ] Test Task 1\t", taskList.toString());

        // Test removing from start of list
        removedTask = taskList.remove(1);
        Assertions.assertEquals("Test Task 1", removedTask.getDescription());
        Assertions.assertEquals(0, taskList.size());
        Assertions.assertEquals("", taskList.toString());
    }

    @Test
    public void testFind() {
        setUp();
        taskList.add(new ToDo("Test Task 1"));
        taskList.add(new ToDo("Test Task 2"));
        Task queryTask = new ToDo("Test Task 3");
        taskList.add(queryTask);

        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(queryTask);

        TaskList actualTaskList = taskList.find("3");
        Assertions.assertEquals(expectedTaskList, actualTaskList);
    }

    @Test
    public void testToString() {
        setUp();
        taskList.add(new ToDo("Test Task 1"));
        taskList.add(new ToDo("Test Task 2"));
        taskList.add(new ToDo("Test Task 3"));

        Assertions.assertEquals("1.[T][ ] Test Task 1\n\t2.[T][ ] Test Task 2\n\t3.[T][ ] Test Task 3\t", taskList.toString());
    }

}
