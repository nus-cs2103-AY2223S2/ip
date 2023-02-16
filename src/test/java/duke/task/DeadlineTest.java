package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTest {

    @Test
    public void deadline_badDate_dukeExceptionThrown() {
        try {
            Deadline a = new Deadline("hello", "bad date");
            a.isMarkedDone();
            fail();
        } catch (DukeException e) {
            assertEquals("Duke: OOPS!!! Write the date in this format: YYYY-MM-DD", e.getMessage());
        }
    }
}
