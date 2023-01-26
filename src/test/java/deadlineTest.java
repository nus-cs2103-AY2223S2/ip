package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class deadlineTest {
    @Test
    public void testToString() {
        assertEquals("[D][ ] go home (by: Oct 15 2019)", new Deadline("deadline", "go home", "2019-10-15").toString());
    }
}