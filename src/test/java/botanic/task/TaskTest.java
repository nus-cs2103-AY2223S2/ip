package botanic.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    /**
     * Test the behaviour of mark().
     */
    @Test
    public void testMark() {
        Task task = new Task("get food");
        task.markIsDone();
        assertEquals("[X] get food", task.toString());
    }

    /**
     * Test the behaviour of unmark().
     */
    @Test
    public void testUnmark() {
        Task task = new Task("get food");
        task.markIsDone();
        task.unmarkIsDone();
        assertEquals("[ ] get food", task.toString());
    }

    /**
     * Test the behaviour of isDone() when a task is not marked.
     */
    @Test
    public void isDone_unmarked_whiteSpace() {
        assertEquals(" ", new Task("a").isDone());
    }

    /**
     * Test the behaviour of isDone() when a task is marked.
     */
    @Test
    public void isDone_marked_markedX() {
        Task task = new Task("a");
        task.markIsDone();
        assertEquals("X", task.isDone());
    }

    /**
     * Test the behaviour of toString().
     */
    @Test
    public void testStringConversion() {
        assertEquals("[ ] get food",
                new Task("get food").toString());
    }

    /**
     * Test the behaviour of formatForStorage().
     */
    @Test
    public void testFormatStore() {
        assertEquals("0 | get food",
                new Task("get food").formatForStorage());
    }
}
