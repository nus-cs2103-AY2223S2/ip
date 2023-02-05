package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    @Test
    void getFileDesc() {
        Deadline test = new Deadline("Test", "2023-05-05");
        assertEquals("D|0|Test|2023-05-05", test.getFileDesc());
        test.mark();
        assertEquals("D|1|Test|2023-05-05", test.getFileDesc());
    }

    @Test
    void testToString() {
        Deadline test = new Deadline("Test", "2023-05-05");
        assertEquals("[D][ ] Test (by: May 05 2023)", test.toString());
        test.mark();
        assertEquals("[D][X] Test (by: May 05 2023)", test.toString());
    }
}