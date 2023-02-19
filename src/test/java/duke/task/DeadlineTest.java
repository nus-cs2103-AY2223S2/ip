package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {

    @Test
    public void testToString() {
        try {
            Deadline t = new Deadline("submit assignment", "2023-05-09");
            String correctDeadline = "[D][ ] submit assignment (by: May 9 2023) \npriority: MEDIUM";
            assertEquals(correctDeadline, t.toString());
        } catch (DukeException e) {
            assertEquals("Please input the date for deadlines in yyyy-mm-dd format!", e.getMessage());
        }
    }

    @Test
    public void testToStringWithDateError() {
        try {
            Deadline t = new Deadline("submit assignment", "9 May 2023");
            String correctDeadline = "[D][ ] submit assignment (by: May 9 2023) \npriority: MEDIUM";
            assertEquals(correctDeadline, t.toString());
        } catch (DukeException e) {
            assertEquals("Please input the date for deadlines in yyyy-mm-dd format!", e.getMessage());
        }
    }
}
