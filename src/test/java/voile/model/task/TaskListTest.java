package voile.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void shouldAddCorrectly() {
        // Note that index starts from 1
        TaskList tasks = new TaskList();
        Task task = new TodoTask("The Woman Who Sold the Moon");
        tasks.add(task);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(1));
    }

    @Test
    public void shouldRemoveCorrectly() {
        Task task = new TodoTask("Bad Apple");
        TaskList tasks = TaskList.of(task);
        Task taskRef = tasks.remove(1);
        assertEquals(0, tasks.size());
        assertTrue(task == taskRef);
    }

    @Test
    public void shouldClearCorrectly() {
        TaskList tasks = TaskList.of(
                new TodoTask("necrofantasia"),
                new TodoTask("Rest In Peace, Saith The Lord"));
        tasks.clear();
        assertEquals(0, tasks.size());
    }
}
