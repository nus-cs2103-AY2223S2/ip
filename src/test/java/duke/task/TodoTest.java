package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testDefaultStringConversion(){
        assertEquals("[T][ ] read book",
                new Todo("read book", false).toString());
        assertEquals("[T][X] READ BOOK",
                new Todo("READ BOOK", true).toString());
        assertEquals("[T][ ] 56 + abc > y",
                new Todo("56 + abc > y", false).toString());
        assertEquals("[T][X] 123",
                new Todo("123", true).toString());
    }

    @Test
    public void testTaskStorageStringConversion(){
        assertEquals("T|false|read book",
                new Todo("read book", false).toTaskStorageString());
        assertEquals("T|true|READ BOOK",
                new Todo("READ BOOK", true).toTaskStorageString());
        assertEquals("T|false|56 + abc > y",
                new Todo("56 + abc > y", false).toTaskStorageString());
        assertEquals("T|true|123",
                new Todo("123", true).toTaskStorageString());
    }

    @Test
    public void taskMarking() {
        Task task = new Todo("read book", false);
        task.mark();

        assertEquals("[T][X] read book", task.toString());
        assertEquals("T|true|read book", task.toTaskStorageString());
    }

    @Test
    public void taskUnmarking() {
        Task task = new Todo("read book", true);
        task.unmark();

        assertEquals("[T][ ] read book", task.toString());
        assertEquals("T|false|read book", task.toTaskStorageString());
    }
}