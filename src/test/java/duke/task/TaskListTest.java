package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    void testGetTasks() {
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        taskList.setTasks(tasks);
        assertEquals(tasks, taskList.getTasks());
    }

    @Test
    void testSetTasks() {
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        taskList.setTasks(tasks);
        assertEquals(tasks, taskList.getTasks());
    }

    @Test
    void testGetSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
    }

    @Test
    void testGetTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void testMarkTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        taskList.addTask(task);
        taskList.markTask(task);
        assertTrue(task.isDone());
    }

    @Test
    void testUnmarkTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        taskList.addTask(task);
        taskList.markTask(task);
        taskList.unmarkTask(task);
        assertFalse(task.isDone());
    }

    @Test
    void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Test task");
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

}
