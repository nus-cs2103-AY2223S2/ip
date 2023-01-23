package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class DeadlineTest {

    @Test
    public void deadline_differentNames_NotEqual() throws DukeException {
        String name1 = "sample name1";
        String name2 = "sample name2";
        String date = "01-02-2003";
        Deadline dl1 = new Deadline(name1, date);
        Deadline dl2 = new Deadline(name2, date);
        assertEquals(dl1.equals(dl2), false);
    }

}
