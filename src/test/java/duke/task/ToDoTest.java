package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToDiskFormat() {
        assertEquals("T|0|read book", new ToDo("read book").toDiskFormat());
        assertEquals("T|1|read book", new ToDo("read book", true).toDiskFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[ ][T] read book", new ToDo("read book").toString());
        assertEquals("[X][T] read book", new ToDo("read book", true).toString());
    }
}
