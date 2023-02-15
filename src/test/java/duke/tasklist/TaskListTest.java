package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void addAndGet() {
        try {
            TaskList tasks = new TaskList();
            Task task0 = new Todo("Write unit tests", false);
            Task task1 = new Deadline("complete unit tests", false, "26/1/2023 2303");
            Task task2 = new Event("unit test celebrations", false, "26/1/2023 2303", "26/1/2023 2359");
            tasks.add(task0);
            tasks.add(task1);
            tasks.add(task2);
            assertEquals(tasks.get(0), task0);
            assertEquals(tasks.get(1), task1);
            assertEquals(tasks.get(2), task2);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void delete() {
        TaskList tasks = new TaskList();
        try {
            Task task0 = new Todo("Write unit tests", false);
            Task task1 = new Deadline("complete unit tests", false, "26/1/2023 2303");
            Task task2 = new Event("unit test celebrations", false, "26/1/2023 2303", "26/1/2023 2359");
            tasks.add(task0);
            tasks.add(task1);
            tasks.add(task2);
            tasks.delete(1);
            assertEquals(tasks.get(0), task0);
            assertEquals(tasks.get(1), task2);
        } catch (Exception e) {
            fail();
        }
        try {
            tasks.get(2);
            fail();
        } catch (DukeException e) {
            assertEquals("The index given is out of range", e.getMessage());
        }
    }

    @Test
    public void matches_testsPattern() {
        TaskList tasks = new TaskList();
        TaskList filteredTasks = null;
        try {
            Task task0 = new Todo("Write unit tests", false);
            Task task1 = new Deadline("complete unit tests", false, "26/1/2023 2303");
            Task task2 = new Event("unit test celebrations", false, "26/1/2023 2303", "26/1/2023 2359");
            tasks.add(task0);
            tasks.add(task1);
            tasks.add(task2);
            filteredTasks = tasks.matches("tests");
            assertEquals(filteredTasks.get(0), task0);
            assertEquals(filteredTasks.get(1), task1);
        } catch (Exception e) {
            fail(e);
        }
        try {
            filteredTasks.get(2);
            fail();
        } catch (DukeException e) {
            assertEquals("The index given is out of range", e.getMessage());
        }
    }
}
