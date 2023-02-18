package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {

    @Test
    public void testToString() {
        try {
            Deadline t = new Deadline("submit assignment", "2023-05-09");
            assertEquals("[D][ ] submit assignment (by: May 9 2023)", t.toString());
        } catch (DukeException e) {
            assertEquals("Please input the date for deadlines in yyyy-mm-dd format!", e.getMessage());
        }
    }

    @Test
    public void testToStringWithError() {
        try {
            Deadline t = new Deadline("submit assignment", "9 May 2023");
            assertEquals("[D][ ] submit assignment (by: May 9 2023)", t.toString());
        } catch (DukeException e) {
            assertEquals("Please input the date for deadlines in yyyy-mm-dd format!", e.getMessage());
        }
    }
}
