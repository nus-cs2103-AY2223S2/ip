package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testMark() {
        Task task = new Task("get food");
        task.mark();
        assertEquals("[X] get food", task.toString());
    }

    @Test
    public void testUnMark() {
        Task task = new Task("get food");
        task.mark();
        task.unmark();
        assertEquals("[ ] get food", task.toString());
    }

    @Test
    public void testGetStatusUnmarked() {
        assertEquals(" ", new Task("a").getStatus());
    }

    @Test
    public void testGetStatusMarked() {
        Task task = new Task("a");
        task.mark();
        assertEquals("X", task.getStatus());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[ ] get food", new Task("get food").toString());
    }

    @Test
    public void testFormatStore() {
        assertEquals("0 | get food", new Task("get food").formatStore());
    }
}
