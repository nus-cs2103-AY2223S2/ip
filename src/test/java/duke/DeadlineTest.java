package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void deadlineDesc_correctDesc_noExceptionThrown() throws InvalidDateTimeException {
        assertEquals("[D][ ] do homework (by: 2023-01-26 15:00)",
                new Deadline("deadline do homework", "26/1/2023 1500", false).toString());
    }

    @Test
    public void deadlineDesc_wrongTime_exceptionThrown() {
        InvalidDateTimeException thrown = assertThrows(InvalidDateTimeException.class, ()
                -> new Deadline("deadline do homework", "26/1/2023 1500", false));
        assertEquals("OOPS!!! Invalid DateTime inputs!",
                thrown.toString());
    }
}
