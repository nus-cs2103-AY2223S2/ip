package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeInvalidCommandExceptionTest {
    @Test
    public void getMessage() {
        String message = DukeInvalidCommandException.INVALID_COMMAND_MESSAGE;
        DukeInvalidCommandException exception = new DukeInvalidCommandException();
        assertEquals(exception.getMessage(), message);
    }

    @Test
    public void getDukeMessage() {
        String message = DukeInvalidCommandException.INVALID_COMMAND_MESSAGE;
        DukeInvalidCommandException exception = new DukeInvalidCommandException();
        assertEquals(exception.getMessage(), message);
        assertEquals(
                exception.getDukeMessage(),
                DukeException.DUKE_MESSAGE_PREFIX + message);
    }
}
