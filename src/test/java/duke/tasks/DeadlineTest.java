package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline d = new Deadline("homework", "2023-01-20 1400");
        assertEquals("[D][ ] homework (by: Jan 20 2023 02:00 PM)", d.toString());
    }
}
