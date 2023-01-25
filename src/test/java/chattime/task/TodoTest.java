package chattime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toDataStringTest() {
        Todo testTask = new Todo("Test");
        assertEquals("T @ 0 @ Test", testTask.toDataString());
    }

    @Test
    public void toStringTest() {
        Todo testTask = new Todo("Test");
        assertEquals("[T][ ] Test", testTask.toString());
    }
}