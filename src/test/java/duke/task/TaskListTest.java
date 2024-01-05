package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTask_validTask_taskAddedToList() {
        TaskList taskList = new TaskList();
        DukeTask task = new TodoTask("Buy milk");
        taskList.addTask(task);
        assertEquals(1, taskList.getNoOfTasks());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void deleteTask_validIndex_taskDeletedFromList() {
        TaskList taskList = new TaskList();
        DukeTask task = new TodoTask("Buy milk");
        taskList.addTask(task);
        assertEquals(1, taskList.getNoOfTasks());
        assertEquals(task, taskList.getTask(0));

        DukeTask deletedTask = taskList.deleteTask(0);
        assertEquals(task, deletedTask);
        assertEquals(0, taskList.getNoOfTasks());
    }

    @Test
    public void getNoOfTasks_emptyList_returnsZero() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getNoOfTasks());
    }

    @Test
    public void getNoOfTasks_listWithTask_returnsNumberOfTasksInList() {
        TaskList taskList = new TaskList();
        DukeTask task = new TodoTask("Buy milk");
        taskList.addTask(task);
        assertEquals(1, taskList.getNoOfTasks());
    }

    @Test
    public void getTask_validIndex_returnsTaskAtIndex() {
        TaskList taskList = new TaskList();
        DukeTask task = new TodoTask("Buy milk");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void getTasks_listWithTask_returnsListOfTasks() {
        TaskList taskList = new TaskList();
        DukeTask task = new TodoTask("Buy milk");
        taskList.addTask(task);
        ArrayList<DukeTask> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void clearTasks_listWithTasks_listIsCleared() {
        TaskList taskList = new TaskList();
        DukeTask task = new TodoTask("Buy milk");
        taskList.addTask(task);
        assertEquals(1, taskList.getNoOfTasks());

        taskList.clearTasks();
        assertEquals(0, taskList.getNoOfTasks());
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        TaskList taskList = new TaskList();
        assertTrue(taskList.isEmpty());
    }
}
