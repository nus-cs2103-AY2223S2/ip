package duke.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void testDukeException() {
        String message = "Invalid input";
        DukeException e = new DukeException(message);
        assertEquals(message, e.getMessage());
    }
}
