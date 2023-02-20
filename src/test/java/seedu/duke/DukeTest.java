package seedu.duke;
import duke.Event;
import duke.Deadline;
import duke.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void eventTest() throws DukeException {
        Event event = new Event("Test", "2021-01-01", "2021-01-03");
        event.isDate();
        assertEquals("[E][ ] Test (from: Jan 1 2021 to: Jan 3 2021)",
                event.toString());
    }

    @Test
    public void deadlineTest() throws DukeException {
        Deadline deadline = new Deadline("dTest", "2021-01-01");
        deadline.markAsDone();
        deadline.isDate();
        assertEquals("[D][X] dTest (by: Jan 1 2021)",
                deadline.toString());

    }
}