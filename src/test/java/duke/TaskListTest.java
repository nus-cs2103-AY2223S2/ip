package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(taskList.getSize(), 0);
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    public void testGetTaskList() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        assertEquals(taskList.getTaskList().get(0), task);
    }

    @Test
    public void testGetTaskListSize() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        assertEquals(taskList.getSize(), 1);
    }

    @Test
    public void testMarkTaskAsDone() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        taskList.markTask(0);
        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    public void testToString() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        assertEquals(taskList.toString(), "1. [ ] test\n");
    }

    @Test
    public void testToStringWithDone() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        taskList.addTask(task);
        taskList.markTask(0);
        assertEquals(taskList.toString(), "1. [X] test\n");
    }

    @Test
    public void testToStringWithMultipleTasks() {
        TaskList taskList = new TaskList();
        Task task = new Task("test");
        Task task2 = new Task("test2");
        taskList.addTask(task);
        taskList.addTask(task2);
        assertEquals(taskList.toString(), "1. [ ] test\n2. [ ] test2\n");
    }
}
