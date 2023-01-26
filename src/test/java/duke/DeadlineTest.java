package duke;

import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void deadlineDesc_correctDesc_noExceptionThrown() throws InvalidDateTimeException {
        assertEquals("[D][ ] do homework (by: 2023-01-26 15:00)",
                Deadline.createDeadline("do homework /by 26/1/2023 1500").toString());
    }

    @Test
    public void deadlineDesc_wrongTime_exceptionThrown() {
        InvalidDateTimeException thrown = assertThrows(InvalidDateTimeException.class,
                () -> Deadline.createDeadline("do homework /by 26/1/2023 2500"));
        assertEquals("OOPS!!! Invalid DateTime inputs!",
                thrown.toString());
    }
}
