package duke.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline t = new Deadline("lash", "2023-04-26 11:00");
        assertEquals("[D][ ]lash (by: 04 26 2023 11:00)", t.toString());
    }
}
