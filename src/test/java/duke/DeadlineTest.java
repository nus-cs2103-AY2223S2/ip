package duke;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void deadlineSuccess() throws DateTimeParseException {
        assertEquals("[D][ ] return book (by: Oct 15 2019)", Deadline.createDeadline("return book /by 2019-10-15").toString());
    }

    @Test
    public void deadlineException() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> Deadline.createDeadline("return book /by 2/12/2019"));
        assertEquals("java.time.format.DateTimeParseException: " + exception.getMessage(), exception.toString());
    }
}
