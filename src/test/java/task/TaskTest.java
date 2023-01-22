package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void givenInvalidTaskType_whenCreate_thenNull() {
        assertNull(Task.create('W', "Content"));
    }

    @Test
    public void givenTodo_whenCreate_thenTodo() {
        Task task = Task.create('T', "Content");
        assertNotNull(task);
        assertEquals(task.getContent(), "Content");
    }

    @Test
    public void givenValidTarget_whenMark_thenMark() {
        Task task = Task.create('T', "Content");
        assertNotNull(task);
        task.mark(true);
        assertTrue(task.isMarked());
        task.mark(false);
        assertFalse(task.isMarked());
    }
}
