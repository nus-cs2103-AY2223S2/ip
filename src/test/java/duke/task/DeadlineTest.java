package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.task.Deadline;
import duke.exception.DukeException;

public class DeadlineTest {
    @Test
    public void toString_validDateTime_success() throws DukeException {
        assertEquals("[D][ ] return book (by: Mon 02-09-2019 12:22PM)", new Deadline("return book /by 2019-09-02 12:22").toString());
    }

    @Test
    public void toString_invalidDateTime_DukeExceptionThrown() throws DukeException {
        try {
            assertEquals("[D][ ] return book (by: Mon 02-09-2019 12:22PM)", new Deadline("return book /by 2019-09-02 12:22:33").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Invalid datetime format. Please use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).\n", e.getMessage());
        }
    }

    @Test
    public void getRawTask_noTestInput_success() throws DukeException {
        assertEquals("D ~ 0 ~ return book ~ 2019-09-02 12:22\n", new Deadline("return book /by 2019-09-02 12:22").getRawTask());
    }
}