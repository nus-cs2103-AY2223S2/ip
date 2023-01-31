package leo.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] run", new ToDoTask("run").toString());
        assertEquals("[T][ ] eat", new ToDoTask("eat").toString());
        assertEquals("[T][ ] study", new ToDoTask("study").toString());
    }

    @Test
    public void testSaveFormat() {
        assertEquals("[T][ ] run\n", new ToDoTask("run").saveFormat());
        assertEquals("[T][ ] eat\n", new ToDoTask("eat").saveFormat());
        assertEquals("[T][ ] study\n", new ToDoTask("study").saveFormat());
    }
}
