package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    void testMarkTask() {
        TaskList tasks = new TaskList();
        Task task = new Todo("Test task");
        tasks.add(task);
        tasks.markTask(0);
        assertTrue(task.isDone());
    }

    @Test
    void testUnmarkTask() {
        TaskList tasks = new TaskList();
        Task task = new Todo("Test task");
        tasks.add(task);
        tasks.markTask(0);
        tasks.unmarkTask(0);
        assertFalse(task.isDone());
    }
}
