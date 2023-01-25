package collections;

import models.Deadline;
import models.Event;
import models.Task;
import models.ToDo;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(taskList.get(1).isDone());
    }

    @Test
    public void testUnMark() {
        setUp();
        taskList.add(new ToDo("Test task"));
        taskList.mark(1);
        taskList.unMark(1);
        assertFalse(taskList.get(1).isDone());
    }

    @Test
    public void testGetTaskListOnDate() {
        setUp();
        taskList.add(new Deadline("Test deadline", "01-01-2022 12:00"));
        taskList.add(new Event("Test event", "01-01-2022 12:00", "01-01-2022 14:00"));
        taskList.add(new ToDo("Test todo"));

        TaskList tasksOnDate = taskList.getTaskListOnDate("01-01-2022");
        assertEquals(2, tasksOnDate.size());
        assertTrue(tasksOnDate.get(1) instanceof Deadline || tasksOnDate.get(1) instanceof Event);
        assertTrue(tasksOnDate.get(2) instanceof Deadline || tasksOnDate.get(2) instanceof Event);
    }

    @Test
    public void testRemove() {
        setUp();
        taskList.add(new ToDo("Test Task 1"));
        taskList.add(new ToDo("Test Task 2"));
        taskList.add(new ToDo("Test Task 3"));

        // Test removing from middle of list
        Task removedTask = taskList.remove(2);
        assertEquals("Test Task 2", removedTask.getDescription());
        assertEquals(2, taskList.size());
        assertEquals("1.[T][ ] Test Task 1\n\t2.[T][ ] Test Task 3\t", taskList.toString());

        // Test removing from end of list
        removedTask = taskList.remove(2);
        assertEquals("Test Task 3", removedTask.getDescription());
        assertEquals(1, taskList.size());
        assertEquals("1.[T][ ] Test Task 1\t", taskList.toString());

        // Test removing from start of list
        removedTask = taskList.remove(1);
        assertEquals("Test Task 1", removedTask.getDescription());
        assertEquals(0, taskList.size());
        assertEquals("", taskList.toString());
    }

    @Test
    public void testToString() {
        setUp();
        taskList.add(new ToDo("Test Task 1"));
        taskList.add(new ToDo("Test Task 2"));
        taskList.add(new ToDo("Test Task 3"));

        assertEquals("1.[T][ ] Test Task 1\n\t2.[T][ ] Test Task 2\n\t3.[T][ ] Test Task 3\t", taskList.toString());
    }
}