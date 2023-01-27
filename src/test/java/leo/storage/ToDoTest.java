package leo.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] run", new ToDo("run").toString());
        assertEquals("[T][ ] eat", new ToDo("eat").toString());
        assertEquals("[T][ ] study", new ToDo("study").toString());
    }

    @Test
    public void testSaveFormat() {
        assertEquals("[T][ ] run\n", new ToDo("run").saveFormat());
        assertEquals("[T][ ] eat\n", new ToDo("eat").saveFormat());
        assertEquals("[T][ ] study\n", new ToDo("study").saveFormat());
    }
}
