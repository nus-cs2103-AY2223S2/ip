package iris.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import iris.task.Todo;

public class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] read book", new Todo("read book").toString());
    }

    @Test
    public void testStorageFormat() {
        assertEquals("T| |read book\n", new Todo("read book").storageFormat());
    }
}
