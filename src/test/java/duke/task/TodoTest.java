package duke.task;

import duke.TaskCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void testCreation() {
        assertDoesNotThrow(() -> new ToDo("test"));
    }

    @Test
    public void testEmptyDescription() {
        TaskCreationException e = assertThrows(TaskCreationException.class, () -> new ToDo(""));
        assertEquals(e.getMessage(), "Description of todo cannot be empty");
    }

    @Test
    public void testToString() throws TaskCreationException {
        assertEquals(new ToDo("Test").toString(), "[T][] Test");
    }
}
