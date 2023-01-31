package leo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import leo.leoexception.IncorrectMarkException;

public class TaskTest {

    @Test
    public void testTaskRetrieval() {
        assertEquals("run", new Task("run").getTask());
        assertEquals("eat", new Task("eat").getTask());
    }

    @Test
    public void testTaskCompletionStatus() throws IncorrectMarkException {
        Task task = new Task("run");
        assertFalse(task.isDone());
        task.mark();
        assertTrue(task.isDone());
    }

    @Test
    public void testSaveFormat() {
        Task task = new Task("run");
        assertEquals("run", task.saveFormat());
    }

    @Test
    public void testType() {
        Task task = new Task("run");
        assertEquals(TaskType.TODO, task.getType());
        task.setType(TaskType.EVENT);
        assertEquals(TaskType.EVENT, task.getType());
        task.setType(TaskType.DEADLINE);
        assertEquals(TaskType.DEADLINE, task.getType());
    }

    @Test
    public void testTypeAndStatus() {
        Task task = new Task("run");
        assertEquals("[T][ ] ", task.typeAndStatus());
        task.setType(TaskType.EVENT);
        assertEquals("[E][ ] ", task.typeAndStatus());
        task.setType(TaskType.DEADLINE);
        assertEquals("[D][ ] ", task.typeAndStatus());
    }
}
