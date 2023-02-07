package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline d = new Deadline("homework", "2024-01-20 1400");
        assertEquals("[D] [ ] homework\n (by: Jan 20 2024 02:00 PM)", d.toString());
    }
}
