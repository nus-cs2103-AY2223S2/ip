package fea.task;

import org.junit.jupiter.api.Test;

import fea.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] test", new Todo("test").toString());
    }
}
