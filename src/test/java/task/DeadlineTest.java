package task;

import org.junit.jupiter.api.Test;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_task_string() {
        assertEquals("[D][ ] assignment (by: JULY 17, 2022)",
                new Deadline("deadline assignment", "2022-07-17").toString());
    }
}
