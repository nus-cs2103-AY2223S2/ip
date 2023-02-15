package duke.test;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoString() {
        ToDo todo = new ToDo("test");
        assertEquals("[T][ ] test", todo.toString());
    }

    @Test
    public void testToDoStorageString() {
        ToDo todo = new ToDo("test");
        assertEquals("T | 0 | test", todo.toStorageString());
    }
}
