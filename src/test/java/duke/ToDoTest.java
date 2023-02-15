package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.NeroException;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testGetTaskIcon() throws NeroException {
        assertEquals("T", new ToDo("run around").getTaskIcon());
    }

    @Test
    public void testToString() throws NeroException {
        assertEquals("[T][ ] eat food", new ToDo("eat food").toString());
    }
}
