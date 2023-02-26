package chattime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private Todo testTask = new Todo("Test");
    @Test
    public void toDataStringTest() {
        assertEquals("T @ 0 @ Test", testTask.toDataString());
    }

    @Test
    public void taskWithCode() {
        assertEquals("[T] Test", testTask.taskWithCode());
    }

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] Test", testTask.toString());
    }
}
