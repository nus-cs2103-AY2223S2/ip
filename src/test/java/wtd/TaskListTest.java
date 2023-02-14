package wtd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import wtd.task.Todo;

public class TaskListTest {
    @Test
    public void testAdd() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test", false));
        assertEquals(1, tasks.size());
        assertEquals("T | 0 | test", tasks.get(0).toFile());
    }

    @Test
    public void testDelete() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test", false));
        tasks.remove(0);
        assertEquals(0, tasks.size());
    }
}
