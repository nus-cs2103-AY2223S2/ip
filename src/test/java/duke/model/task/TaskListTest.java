package duke.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void shouldAddTaskCorrectly() {
        // Note that index starts from 1
        TaskList lst = new TaskList();
        Task task = new TodoTask("The Woman Who Sold the Moon");
        lst.add(task);
        assertEquals(1, lst.size());
        assertEquals(task, lst.get(1));
    }

    @Test
    public void shouldRemoveTaskCorrectly() {
        Task task = new TodoTask("Bad Apple");
        TaskList lst = TaskList.of(task);
        Task taskRef = lst.remove(1);
        assertEquals(0, lst.size());
        assertTrue(task == taskRef);
    }
}
