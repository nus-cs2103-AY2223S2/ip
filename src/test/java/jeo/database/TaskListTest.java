package jeo.database;

import jeo.task.StubTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testNumberOfTasksAfterAdding() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(new StubTask("run"));
        testTaskList.addTask(new StubTask("bathe"));
        testTaskList.addTask(new StubTask("sleep"));
        assertEquals(3, testTaskList.getNumberOfTasks());
    }

    @Test
    public void testNumberOfTasksAfterDeleting() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(new StubTask("run"));
        testTaskList.addTask(new StubTask("bathe"));
        testTaskList.addTask(new StubTask("sleep"));
        testTaskList.deleteTask(2);
        assertEquals(2, testTaskList.getNumberOfTasks());
    }

    @Test
    public void testTaskAtIndex() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(new StubTask("run"));
        testTaskList.addTask(new StubTask("bathe"));
        testTaskList.addTask(new StubTask("sleep"));
        assertEquals("sleep", testTaskList.getTaskAtIndex(2).toString());
    }

}
