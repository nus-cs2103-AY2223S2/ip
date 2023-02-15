package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskTrackerTest {
    @Test
    public void testAddTodo() {
        TaskTracker tt = new TaskTracker();
        String taskDesc = "hello, I am a task!";
        Task newTask = tt.addTodo(taskDesc);
        Task t;
        try {
            t = tt.getTask(0);
        } catch (TaskNotFoundException e) {
            t = null;
        }
        assertEquals(newTask, t);
    }

    @Test
    public void testMarkTest() {
        TaskTracker tt = new TaskTracker();
        String taskDesc = "hello, I am a task!";
        tt.addTodo(taskDesc);
        Task t;
        try {
            tt.markTask(0);
            t = tt.getTask(0);
        } catch (DukeException e) {
            t = null;
        }
        assertEquals("[X]", t.getStatusIndicator());
    }

    @Test
    public void testDeleteTask() {
        TaskTracker tt = new TaskTracker();
        String taskDesc = "hello, I am a task!";
        Task newTask = tt.addTodo(taskDesc);
        Task t;
        try {
            tt.deleteTask(0);
        } catch (DukeException e) {
            t = null;
        }
        assertEquals("", tt.listTasks());
    }
}
