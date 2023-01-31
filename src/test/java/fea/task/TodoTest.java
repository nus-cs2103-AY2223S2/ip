package fea.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] test", new Todo("test").toString());
    }
}
