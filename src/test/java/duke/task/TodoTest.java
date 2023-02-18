package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        Todo t = new Todo("test");
        assertEquals("[T][ ] test", t.toString());
    }

    @Test
    public void testMark() {
        Todo t = new Todo("test");
        t.mark();
        assertEquals("[T][X] test", t.toString());
    }
}
