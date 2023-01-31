package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testStringConversion() {
        assertEquals("[ ] read book", new Task("read book").toString());
    }

    @Test
    public void testGetRawTask() {
        assertEquals("T ~ 0 ~ read book\n", new Task("read book").getRawTask());
    }
}