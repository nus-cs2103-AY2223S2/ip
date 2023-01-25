package duke.tasks;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void deadlineTestUi() throws DukeException {
        Deadline deadline = new Deadline("meeting", "2022-02-23", false);
        assertEquals("\t[D][ ] meeting (by: Feb 23 2022)", deadline.toString());
    }
}