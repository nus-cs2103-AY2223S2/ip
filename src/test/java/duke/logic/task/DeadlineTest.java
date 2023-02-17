package duke.logic.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {

    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("[D][ ] do quiz (by: Feb 15 2019 10:45)",
                Deadline.create(" do quiz /by 2019-02-15 22:45").toString());
    }
}
