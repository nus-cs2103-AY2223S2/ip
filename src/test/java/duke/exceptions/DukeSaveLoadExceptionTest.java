package duke.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeSaveLoadExceptionTest {
    @Test
    public void getMessage() {
        String message = "ERROR MESSAGE";
        DukeSaveLoadException exception = new DukeSaveLoadException(message);
        assertEquals(exception.getMessage(), message);
    }

    @Test
    public void getDukeMessage() {
        String message = "ERROR MESSAGE";
        DukeSaveLoadException exception = new DukeSaveLoadException(message);
        assertEquals(exception.getMessage(), message);
        assertEquals(
                exception.getDukeMessage(), 
                DukeException.DUKE_MESSAGE_PREFIX + message);
    }
}
