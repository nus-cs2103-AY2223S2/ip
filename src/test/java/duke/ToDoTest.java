package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testGetTaskIcon() {
        assertEquals("T", new ToDo("run around").getTaskIcon());
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] eat food", new ToDo("eat food").toString());
    }
}
