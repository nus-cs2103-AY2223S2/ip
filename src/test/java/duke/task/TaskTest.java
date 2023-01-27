package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testMark() {
        Task task = new Task("get food");
        task.markIsDone();
        assertEquals("[X] get food", task.toString());
    }

    @Test
    public void testUnmark() {
        Task task = new Task("get food");
        task.markIsDone();
        task.unmarkIsDone();
        assertEquals("[ ] get food", task.toString());
    }

    @Test
    public void isDone_unmarked_whiteSpace() {
        assertEquals(" ", new Task("a").isDone());
    }

    @Test
    public void isDone_marked_markedX() {
        Task task = new Task("a");
        task.markIsDone();
        assertEquals("X", task.isDone());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[ ] get food",
                new Task("get food").toString());
    }

    @Test
    public void testFormatStore() {
        assertEquals("0 | get food",
                new Task("get food").formatForStorage());
    }
}
