package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void addTodoTest() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            taskList.addTask(new Todo("todo Dummy Task"));
        } catch (DukeException err) {
            fail();
        }
        assertEquals(1, taskList.getNumTasks());
    }

    @Test
    public void validateTodoTask() {
        try {
            Todo todo = new Todo("todo ");
            fail();
        } catch (DukeException err) {
            return;
        }
    }

    @Test
    public void markTodoTest() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            taskList.addTask(new Todo("todo Dummy Task"));
            taskList.markTask(1);
        } catch (DukeException err) {
            System.out.println("Ignore exception: For testing purposes");
        }
        assertEquals("X", taskList.getTasks().get(0).getStatusIcon());

    }
}
