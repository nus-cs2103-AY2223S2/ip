package reborn.data;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import reborn.data.task.Todo;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Do homework"));
        assertEquals(1, tasks.size());
        assertEquals("T | 0 | Do homework", tasks.get(0).storageStr());
    }

    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Do homework"));
        tasks.add(new Todo("Go run"));
        assertEquals(2, tasks.size());
        assertEquals("T | 0 | Do homework" + System.lineSeparator()
                + "T | 0 | Go run" + System.lineSeparator(), tasks.tasksToStr());
        tasks.remove(1);
        assertEquals(1, tasks.size());
        assertEquals("T | 0 | Do homework" + System.lineSeparator(), tasks.tasksToStr());
    }
}
