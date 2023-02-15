package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class EventTest {
    @Test
    public void toString_validDateTime_success() throws DukeException {
        assertEquals("[E][ ] internship\n(from: Mon 02-09-2024 12:22PM\nto: Tue 02-09-2025 12:22PM)",
                new Event("internship /from 2024-09-02 12:22 /to 2025-09-02 12:22").toString());
    }

    @Test
    public void toString_invalidDateTime_dukeExceptionThrown() throws DukeException {
        try {
            assertEquals("[E][ ] internship\n(from: Mon 02-09-2024 12:22PM to: Tue 02-09-2025 12:22PM)\n"
                            + "!! TASK EXPIRING SOON! !!",
                    new Event("internship /from 2024-09-02 12:22 /to 2025-09-02 12").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid datetime format. "
                            + "Please use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).\n",
                    e.getMessage());
        }
    }

    @Test
    public void getRawTask_noTestInput_success() throws DukeException {
        assertEquals("E ~ 0 ~ internship ~ 2024-09-02 12:22 ~ 2025-09-02 12:22\n",
                new Event("internship /from 2024-09-02 12:22 /to 2025-09-02 12:22").getRawTask());
    }
}
