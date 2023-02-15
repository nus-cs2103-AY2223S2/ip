package duke.task;

import duke.TaskCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
