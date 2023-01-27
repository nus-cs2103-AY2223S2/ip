package genie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {
    @Test
    public void testToStringConversion() {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
        ToDo t = new ToDo("read book");
        t.markDone();
        assertEquals("[T][X] read book", t.toString());
    }
    @Test
    public void testToFileFormatConversion() {
        assertEquals("[T][ ] read book", new ToDo("read book").toFileFormat());
    }
}
