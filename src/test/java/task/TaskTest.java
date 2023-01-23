package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void create_invalidTaskType_nullReturned() {
        assertNull(Task.create('W', "Content"));
    }

    @Test
    public void create_validTodo_todoReturned() {
        Task task = Task.create('T', "Content");
        assertNotNull(task);
        assertEquals(task.getContent(), "Content");
    }

    @Test
    public void mark_validTarget_taskMarked() {
        Task task = Task.create('T', "Content");
        assertNotNull(task);
        task.mark(true);
        assertTrue(task.isMarked());
        task.mark(false);
        assertFalse(task.isMarked());
    }
}
