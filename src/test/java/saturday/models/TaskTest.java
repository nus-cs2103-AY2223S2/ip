package saturday.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class TaskTest {

    @Test
    public void testTaskConstructor() {
        Task task = new Task("Finish homework");
        assertEquals("Finish homework", task.getDescription());
    }

    @Test
    public void testTaskMark() {
        Task task = new Task("Finish homework");
        task.mark();
        assertTrue(task.toString().startsWith("[X]"));
    }

    @Test
    public void testTaskUnMark() {
        Task task = new Task("Finish homework");
        task.mark();
        task.unMark();
        assertTrue(task.toString().startsWith("[ ]"));
    }

    @Test
    public void testContains() {
        Task task = new Task("Finish homework");
        assertTrue(task.contains("homework"));
    }

    @Test
    public void testTaskToString() {
        Task task = new Task("Finish homework");
        assertEquals("[ ] Finish homework", task.toString());
    }
}
