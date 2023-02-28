package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void stringTest() {
        var t = new Todo("read", false);
        assertEquals("   [T][ ] read\n", t.toString());
    }

    @Test
    public void writeTest() {
        var t = new Todo("read", false);
        assertEquals("T | 0 | read |\n", t.toWrite());
    }
}
