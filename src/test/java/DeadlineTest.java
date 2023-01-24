import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.ToDo;
import org.junit.jupiter.api.Test;

import duke.Deadline;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ]read book (by:Sunday 4pm)",
                new Deadline("read book", "Sunday 4pm").toString());
    }
}
