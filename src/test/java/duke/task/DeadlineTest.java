package duke.task;

import duke.TaskCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void testCreation() {
        assertDoesNotThrow(() -> new Deadline("test", "2022-01-01"));
    }

    @Test
    public void wrongDate_wrongDate_throwsException() {
        TaskCreationException e = assertThrows(TaskCreationException.class, () -> new Deadline("test", "1"));
        assertEquals(e.getMessage(), "Error parsing date");
    }
}
