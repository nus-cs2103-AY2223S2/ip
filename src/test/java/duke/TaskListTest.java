package duke;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;
import duke.tasks.Todo;
public class TaskListTest {
    @Test
    public void testTaskList() {
        TaskList tasklist = new TaskList();
        assertAll(() -> {
            Task task = new Todo("play", false);
            assertEquals("[T][   ] play", task.toString());
        });
    }
}
