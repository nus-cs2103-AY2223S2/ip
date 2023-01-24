package duke.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void testDukeException() {
        String message = "Invalid input";
        DukeException e = new DukeException(message);
        assertEquals(message, e.getMessage());
    }
}
