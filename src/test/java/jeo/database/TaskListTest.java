package jeo.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeo.task.StubTask;

public class TaskListTest {

    @Test
    public void testNumberOfTasks_afterAdding() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(new StubTask("run"));
        testTaskList.addTask(new StubTask("bathe"));
        testTaskList.addTask(new StubTask("sleep"));
        assertEquals(3, testTaskList.getNumberOfTasks());
    }

    @Test
    public void testNumberOfTasks_afterDeleting() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(new StubTask("run"));
        testTaskList.addTask(new StubTask("bathe"));
        testTaskList.addTask(new StubTask("sleep"));
        testTaskList.deleteTask(2);
        assertEquals(2, testTaskList.getNumberOfTasks());
    }

    @Test
    public void testTask_atIndex() {
        TaskList testTaskList = new TaskList();
        testTaskList.addTask(new StubTask("run"));
        testTaskList.addTask(new StubTask("bathe"));
        testTaskList.addTask(new StubTask("sleep"));
        assertEquals("sleep", testTaskList.getTaskAtIndex(2).toString());
    }

}
